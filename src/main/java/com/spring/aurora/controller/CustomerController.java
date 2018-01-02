package com.spring.aurora.controller;

import com.spring.aurora.model.Customer;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    CustomerFormValidator customerFormValidator;
    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(customerFormValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.debug("index()");
        return "redirect:/customers/list";
    }
    @RequestMapping(value = "/customers/list", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        logger.info("List Customer form.");
        model.addAttribute("customers", customerService.findAll());
        return "list-customers";
    }

    @RequestMapping(value = "/customers/new", method = RequestMethod.GET)
    public String newCustomer(Model model) {
        logger.debug("New Customer form.");
        model.addAttribute("customerForm", new Customer());
        return "new-customer";
    }

    @RequestMapping(value = "/customers/save", method = RequestMethod.POST)
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
                redirectAttributes.addFlashAttribute("msg", "User added successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
            }

            customerService.insert(customer);

            // POST/REDIRECT/GET
            return "redirect:/customers/list";
            //return "redirect:/customers/" + customer.getCustomerId();
        }
    }
}
