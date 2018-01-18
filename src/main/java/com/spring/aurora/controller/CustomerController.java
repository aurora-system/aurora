package com.spring.aurora.controller;

import com.spring.aurora.model.Customer;
import com.spring.aurora.model.Order;
import com.spring.aurora.service.CustomerService;
import com.spring.aurora.util.CustomerFormValidator;
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
        model.addAttribute("customer", customerService.view(customerId));
        return "view-customer";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCustomer(Model model) {
        logger.debug("New Customer form.");
        model.addAttribute("customerForm", new Customer());
        model.addAttribute("types",new String[]{" Business "," Residential "});
        return "new-customer";
    }
    
    // TODO: Transfer to OrderController
    @RequestMapping(value = "/neworder", method = RequestMethod.GET)
    public String newOrder(@ModelAttribute(value="customerId") String customerId, Model model) {
        logger.debug("New Order form for customer: " + customerId);
        Order order = new Order();
        order.setCustomerId(customerId);
        model.addAttribute("orderForm", order);
        model.addAttribute("customerId", customerId); // TODO: Show the customer name but id is submitted (hidden)
        // TODO: Assign delivery receipt number 
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
            if(customer.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Customer created successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Customer updated successfully!");
            }

            customerService.insert(customer);

            // POST/REDIRECT/GET
            return "redirect:/customers/list";
            //return "redirect:/customers/" + customer.getCustomerId();
        }
    }
}
