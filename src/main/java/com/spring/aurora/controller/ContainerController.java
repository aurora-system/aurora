package com.spring.aurora.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.aurora.entity.ContainerCustomerEntity;
import com.spring.aurora.entity.DebtCustomerEntity;
import com.spring.aurora.model.Container;
import com.spring.aurora.model.Customer;
import com.spring.aurora.model.Debt;
import com.spring.aurora.service.ContainerService;
import com.spring.aurora.service.CustomerService;

@Controller
@RequestMapping("/container")
public class ContainerController {

    private static final Logger logger = LoggerFactory.getLogger(ContainerController.class);

    @Autowired private ContainerService containerService;
    @Autowired private CustomerService customerService;

    @RequestMapping(value = "/return", method = RequestMethod.GET)
    public String returnContainer(@RequestParam String cid, Model model) {
        Container container = new Container();
        container.setCustomerId(cid);
        container.setStatus("R");
        Customer customer = customerService.view(cid);
        model.addAttribute("container", container);
        model.addAttribute("customer", customer);
        return "return-container";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveContainerActivity(@ModelAttribute("container") Container container,
                           BindingResult result, Model model,
                           final RedirectAttributes redirect){
        if (result.hasErrors()) {
            return "return-container";
        } else {
            redirect.addFlashAttribute("msg", "Returned containers logged successfully!");
        }
        container.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        
        containerService.insert(container);
        return "redirect:/customers/view?customerId=" +container.getCustomerId();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listDebts(@RequestParam String cid, Model model) {
       
       return "list-debts";
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public String listAllDebts(Model model) {
    	List<Customer> customers = customerService.findAll();
        Map<String, Object> containersMap = customers.stream().collect(Collectors.toMap(Customer::getCustomerId,
                customer -> {return new ContainerCustomerEntity(
                        customer.getName(), getSlimTotal(customer.getCustomerId()), getRoundTotal(customer.getCustomerId()));}));
        model.addAttribute("containersMap", containersMap);
        
        return "container-totals";
    }

    public int getSlimTotal (String cid) {
    	
    	List<Container> containers = containerService.findAllByCustomerId(cid);
    	int total = 0;
    	
    	for (Container c : containers) {
    		if (c.getStatus().equalsIgnoreCase("B")) {
    			total += c.getSlimCount();
    		} else {
    			total -= c.getSlimCount();
    		}
    	}
    	
    	return total;
    }
    
    public int getRoundTotal (String cid) {
    	
    	List<Container> containers = containerService.findAllByCustomerId(cid);
    	int total = 0;
    	
    	for (Container c : containers) {
    		if (c.getStatus().equalsIgnoreCase("B")) {
    			total += c.getRoundCount();
    		} else {
    			total -= c.getRoundCount();
    		}
    	}
    	
    	return total;
    }
}
