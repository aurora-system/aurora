package com.spring.aurora.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.aurora.entity.OrderCustomerEntity;
import com.spring.aurora.model.Container;
import com.spring.aurora.model.Customer;
import com.spring.aurora.model.Debt;
import com.spring.aurora.model.Order;
import com.spring.aurora.service.ContainerService;
import com.spring.aurora.service.CustomerService;
import com.spring.aurora.service.DebtService;
import com.spring.aurora.service.OrderService;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ContainerService containerService;
    
    @Autowired
    private DebtService debtService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listOrders(Model model) {
    	logger.info("List all orders.");
        
    	List<Order> orderList = new ArrayList<>();
    	List<OrderCustomerEntity> orderCustomerEntityList = new ArrayList<>();
        
    	orderList = orderService.findAll();
        
        for (Order order : orderList) {
        	OrderCustomerEntity oce = new OrderCustomerEntity();
        	oce.setOrder(order);
        	Customer customer = customerService.view(order.getCustomerId());
            oce.setCustomerName(customer.getContactName());
            orderCustomerEntityList.add(oce);
        }
        model.addAttribute("orders", orderCustomerEntityList);
        return "list-orders";
    }

    @RequestMapping("/{customerId}")
    public List<Order> findAllOrdersByCustomerId(@PathVariable String customerId) {
        List<Order> orders = new ArrayList<>();

        return orders;
    }

    @RequestMapping("/{customerId}/new")
    public Order saveOrderByCustomer(Order order) {
        return order;
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOrder(@ModelAttribute("orderForm") @Validated Order order,
                               BindingResult result, Model model,
                               final RedirectAttributes redirectAttributes) {
        logger.debug("Save order.");
        if (result.hasErrors()) {
            //populateDefaultModel(model);
            return "new-order";
        } else {
            // Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");
            if(order.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Order created successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Order updated successfully!");
            }

            order.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            
            orderService.insert(order);
            
            saveDebt(order.getAmountPaid(), order.getTotalAmount(), order.getCustomerId());
            
            saveContainerActivity(order.getSlimCount(), order.getRoundCount(), order.getSlimReturned(), order.getRoundReturned(), order.getCustomerId());

            // POST/REDIRECT/GET
            return "redirect:/customers/list";
            //return "redirect:/customers/" + customer.getCustomerId();
        }
    }
    
    /**
     * Saves the debt of the customer if amount paid is less than total amount for a given order.
     * 
     * @param amountPaid
     * @param totalAmount
     * @param customerId
     */
    public void saveDebt (double amountPaid, double totalAmount, String customerId) {
    	
    	double deficit = totalAmount - amountPaid;
    	
    	if (deficit != 0.0) {
    		Debt debtEntry = new Debt();
    		debtEntry.setCustomerId(customerId);
    		debtEntry.setAmount(deficit);
    		debtEntry.setRemarks("Total amount is: Php" + totalAmount + " but the amount paid is only Php" + amountPaid);
    		debtEntry.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
    		debtService.insert(debtEntry);
    	}
    }
    
    /**
     * Saves the returned and borrowed containers into the Container table.
     * 
     * @param slimCount
     * @param roundCount
     * @param slimReturned
     * @param roundReturned
     * @param customerId
     */
    public void saveContainerActivity (int slimCount, int roundCount, int slimReturned, int roundReturned, String customerId) {
    	
    	Container containerActivity = new Container();
        containerActivity.setCustomerId(customerId);
        containerActivity.setRoundCount(roundCount);
        containerActivity.setSlimCount(slimCount);
        containerActivity.setStatus("B");
        containerActivity.setDate(Timestamp.valueOf(LocalDateTime.now()));
        containerService.insert(containerActivity);
        
        if (slimReturned != 0 || roundReturned != 0) {
        	containerActivity = new Container();
            containerActivity.setCustomerId(customerId);
            containerActivity.setRoundCount(roundReturned);
            containerActivity.setSlimCount(slimReturned);
            containerActivity.setStatus("R");
            containerActivity.setDate(Timestamp.valueOf(LocalDateTime.now()));
            containerService.insert(containerActivity);
        }
    }
}
