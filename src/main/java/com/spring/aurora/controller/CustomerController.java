package com.spring.aurora.controller;

import com.spring.aurora.model.Container;
import com.spring.aurora.model.Customer;
import com.spring.aurora.model.Debt;
import com.spring.aurora.model.Order;
import com.spring.aurora.service.*;
import com.spring.aurora.util.CustomerFormValidator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/customers")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ContainerService containerService;
    
    @Autowired
    private DebtService debtService;

    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private ExpenseService expenseService;
    
    // Temporarily disabled this while new-order is not yet transferred to order controller
//    @Autowired
//    CustomerFormValidator customerFormValidator;
    
    //Set a form validator
//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(customerFormValidator);
//    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchCustomer(Model model) {
        logger.info("Display customer search result.");
        //model.addAttribute("customers", customerService.findAll());
        return "customer-search-result";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        logger.info("List all customers.");
        model.addAttribute("customers", customerService.findAll());
        return "list-customers";
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCustomer(Model model, @RequestParam String customerId) {
        logger.info("Display customer information.");
        
        List<Order> orderList = new ArrayList<>();
        orderList = orderService.findAllByCustomerId(customerId);
        
        model.addAttribute("customer", customerService.view(customerId));
        model.addAttribute("orders", orderList);
        
        Timestamp mostRecentOd = orderService.getMostRecentOrderDate(customerId);
        
        if (mostRecentOd != null) {
        	Date date = new Date();
            date.setTime(mostRecentOd.getTime());
            String formattedDate = new SimpleDateFormat("MMM dd YYYY @ hh:mm a").format(date);
            model.addAttribute("mostRecentOrderDate", formattedDate);
        } else {
        	model.addAttribute("mostRecentOrderDate", "No orders yet.");
        }
        
        List<Container> containerList = new ArrayList<>();
        containerList = containerService.findAllByCustomerId(customerId);
        
        int totalRoundBorrowed = 0;
        int totalSlimBorrowed = 0;
        
        for (Container c : containerList) {   
        	if (c.getStatus().equalsIgnoreCase("B")) {
        		totalRoundBorrowed = totalRoundBorrowed +  c.getRoundCount();
        		totalSlimBorrowed = totalSlimBorrowed + c.getSlimCount();
        	} else {
        		totalRoundBorrowed = totalRoundBorrowed - c.getRoundCount();
        		totalSlimBorrowed = totalSlimBorrowed - c.getSlimCount();
        	}
        }
        
        model.addAttribute("totalBorrowedRound", totalRoundBorrowed);
    	model.addAttribute("totalBorrowedSlim", totalSlimBorrowed);
        
    	double debtsSubTotal = debtService.findDebtsTotalByCustomerId(customerId);
        double paymentsSubTotal = paymentService.getPaymentsTotalByCustomerId(customerId);
        double totalDebt = debtsSubTotal - paymentsSubTotal;
        model.addAttribute("totalDebt", totalDebt);
    	
        return "view-customer";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCustomer(Model model) {
        logger.debug("New Customer form.");
        Customer customer = new Customer();
        customer.setType("Business");
        model.addAttribute("customerForm", customer);
        model.addAttribute("types",new String[]{" Business "," Residential "});
        return "new-customer";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editCustomer(Model model, String customerId) {
        logger.debug("Edit Customer form.");
        
        Customer customer = new Customer(); 
        customer = customerService.view(customerId);
        model.addAttribute("customerForm", customer);
        model.addAttribute("types",new String[]{" Business "," Residential"});
        return "edit-customer";
    }
    
    // TODO: Transfer to OrderController
    @RequestMapping(value = "/neworder", method = RequestMethod.GET)
    public String newOrder(@ModelAttribute(value="customerId") String customerId, Model model) {
        logger.debug("New Order form for customer: " + customerId);
        Order order = new Order();
        order.setCustomerId(customerId);
        model.addAttribute("orderForm", order);
        model.addAttribute("customerId", customerId);
        model.addAttribute("newDrNumber", orderService.getNewDrNumber());
        
        Customer customer = customerService.view(customerId);
        model.addAttribute("customerName", customer.getName());
        return "new-order";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customerForm") @Validated Customer customer,
                               BindingResult result, Model model,
                               final RedirectAttributes redirectAttributes) {
        logger.debug("Save customer.");
        if (result.hasErrors()) {
            //populateDefaultModel(model);
            return "new-customer";
        } else {
            // Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");
            
            customerService.insert(customer);
            redirectAttributes.addFlashAttribute("msg", "Customer created successfully!");
            
//            	customerService.update(customer);
//                redirectAttributes.addFlashAttribute("msg", "Customer updated successfully!");

            // POST/REDIRECT/GET
            return "redirect:/customers/list";
            //return "redirect:/customers/" + customer.getCustomerId();
        }
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCustomer(@ModelAttribute("customerForm") @Validated Customer customer,
                               BindingResult result, Model model,
                               final RedirectAttributes redirectAttributes) {
        logger.debug("Update customer.");
        if (result.hasErrors()) {
            //populateDefaultModel(model);
            return "new-customer";
        } else {
            // Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");

            customerService.update(customer);
			redirectAttributes.addFlashAttribute("msg", "Customer updated successfully!");

            // POST/REDIRECT/GET
            return "redirect:/customers/list";
            //return "redirect:/customers/" + customer.getCustomerId();
        }
    }
}
