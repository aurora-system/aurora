package com.spring.aurora.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.aurora.entity.DailySalesEntity;
import com.spring.aurora.entity.OrderCustomerEntity;
import com.spring.aurora.model.Container;
import com.spring.aurora.model.Customer;
import com.spring.aurora.model.Debt;
import com.spring.aurora.model.Expense;
import com.spring.aurora.model.Order;
import com.spring.aurora.model.Payment;
import com.spring.aurora.service.ContainerService;
import com.spring.aurora.service.CustomerService;
import com.spring.aurora.service.DebtService;
import com.spring.aurora.service.ExpenseService;
import com.spring.aurora.service.OrderService;
import com.spring.aurora.service.PaymentService;

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
    
    @Autowired
    private ExpenseService expenseService;
    
    @Autowired
    private PaymentService paymentService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    public String dailySales(Model model, @RequestParam(value="d", defaultValue="today", required=false) String d ) {
        logger.info("Daily sales report.");
        
        int totalSlimDelivered = 0;
        int totalRoundDelivered = 0;
        int totalSlimReturned = 0;
        int totalRoundReturned = 0;
        Double totalExpenses = 0.0;
        Double totalPayments = 0.0;
        Double totalDebt = 0.0;
        
        List<DailySalesEntity> dseList = new ArrayList<>();
        
        Date date = ("today".equalsIgnoreCase(d)) ? Date.valueOf(LocalDate.now()) : Date.valueOf(LocalDate.parse(d));
        String datePicked = new SimpleDateFormat("MMM dd YYYY").format(date);
        model.addAttribute("datePicked", datePicked);
        
        List<Order> orderList = orderService.findAllOrdersToday(date);
        
        for (Order o : orderList) {
        	DailySalesEntity dse = new DailySalesEntity();
        	dse.setOrder(o);
        	
        	Customer c = customerService.view(o.getCustomerId());
        	dse.setCustomerName(c.getName());
        	totalPayments += o.getAmountPaid();
        	dse.setPaidAmount(o.getAmountPaid());
        	
        	totalSlimDelivered += o.getSlimCount();
        	totalRoundDelivered += o.getRoundCount();
        	totalSlimReturned += o.getSlimReturned();
        	totalRoundReturned += o.getRoundReturned();
        	
        	Double debt = o.getTotalAmount() - o.getAmountPaid();
        	totalDebt += debt;
        	dse.setBalanceAmount(debt);
        	dse.setDateAndTime(o.getCreatedAt());
        	dseList.add(dse);
        }
        
        List<Expense> expenseList = new ArrayList<>();
        expenseList = expenseService.findAllByDate(date);
        
        for (Expense e : expenseList) {
        	DailySalesEntity dse = new DailySalesEntity();
        	dse.setCustomerName(e.getDescription());
        	totalExpenses += e.getAmount();
        	dse.setExpenseAmount(e.getAmount());
        	dseList.add(dse);
        }
        
        List<Payment> paymentList = new ArrayList<>();
        paymentList = paymentService.findAllByDate(date);
        
        for (Payment p : paymentList) {
        	DailySalesEntity dse = new DailySalesEntity();
        	dse.setCustomerName(p.getRemarks());
        	totalPayments += p.getAmount();
        	dse.setPaidAmount(p.getAmount());
        	dseList.add(dse);
        }
        
        model.addAttribute("dailySales", dseList);
        
        model.addAttribute("totalSlimDelivered", totalSlimDelivered);
        model.addAttribute("totalRoundDelivered", totalRoundDelivered);
        model.addAttribute("totalSlimReturned", totalSlimReturned);
        model.addAttribute("totalRoundReturned", totalRoundReturned);
        
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("totalPayments", totalPayments);
        model.addAttribute("totalDebt", totalDebt);
        
        model.addAttribute("ar", totalPayments - totalExpenses);
        return "daily-sales";
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
            oce.setCustomerName(customer.getName());
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
    
    @RequestMapping("/cancel")
	public String cancelOrder(Order order, BindingResult result, @RequestParam String orderId,
			final RedirectAttributes redirectAttributes) {

    	if (result.hasErrors()) {
            return "list-orders";
        } else {
        	orderService.cancelOrder(order);
        	redirectAttributes.addFlashAttribute("msg", "Order has been cancelled.");
        }
    	
    	return "redirect:/orders/list";
    }
    
    @RequestMapping("/deliver")
	public String deliverOrder(Order order, BindingResult result, @RequestParam String orderId,
			final RedirectAttributes redirectAttributes) {

    	if (result.hasErrors()) {
            return "list-orders";
        } else {
        	
        	order = orderService.findOrderByOrderId(orderId);
        	saveContainerActivity(order.getSlimCount(), order.getRoundCount(), order.getSlimReturned(), order.getRoundReturned(), order.getCustomerId());
        	
        	orderService.setToDelivered(orderId);
        	redirectAttributes.addFlashAttribute("msg", "Order has been set to delivered.");
        }
    	
    	return "redirect:/orders/list";
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
            order.setStatus("Pending");
            
            orderService.insert(order);
            
            saveDebt(order.getAmountPaid(), order.getTotalAmount(), order.getCustomerId(), order.getOrderId());
            
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
    public void saveDebt (double amountPaid, double totalAmount, String customerId, String orderId) {
    	
    	double deficit = totalAmount - amountPaid;
    	
    	if (deficit != 0.0) {
    		Debt debtEntry = new Debt();
    		debtEntry.setCustomerId(customerId);
    		debtEntry.setAmount(deficit);
    		debtEntry.setRemarks("Total amount is: Php" + totalAmount + " but the amount paid is only Php" + amountPaid);
    		debtEntry.setCreatedAt(Date.valueOf(LocalDate.now()));
    		debtEntry.setOrderId(orderId);
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
        containerActivity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        containerService.insert(containerActivity);
        
        if (slimReturned != 0 || roundReturned != 0) {
        	containerActivity = new Container();
            containerActivity.setCustomerId(customerId);
            containerActivity.setRoundCount(roundReturned);
            containerActivity.setSlimCount(slimReturned);
            containerActivity.setStatus("R");
            containerActivity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            containerService.insert(containerActivity);
        }
    }
}
