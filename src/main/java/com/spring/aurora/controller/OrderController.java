package com.spring.aurora.controller;

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
import com.spring.aurora.model.Order;
import com.spring.aurora.service.ContainerService;
import com.spring.aurora.service.CustomerService;
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

            java.util.Date today = new java.util.Date();
            order.setCreatedAt(new java.sql.Timestamp(today.getTime()));
            
            orderService.insert(order);
            
            // Borrowed containers - TODO we should put into consideration the returned containers
            Container containerActivity = new Container();
            containerActivity.setCustomerId(order.getCustomerId());
            containerActivity.setRoundCount(order.getRoundCount());
            containerActivity.setSlimCount(order.getSlimCount());
            containerActivity.setStatus("B");
            containerActivity.setDate(new java.sql.Timestamp(today.getTime()));
            containerService.insert(containerActivity);

            // POST/REDIRECT/GET
            return "redirect:/customers/list";
            //return "redirect:/customers/" + customer.getCustomerId();
        }
    }
}
