package com.spring.aurora.controller;

import java.util.ArrayList;
import java.sql.Date;
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

import com.spring.aurora.model.Order;
import com.spring.aurora.service.OrderService;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listOrders(Model model) {
    	logger.info("List all orders.");
        List<Order> orders = new ArrayList<>();
        orders = orderService.findAll();
        model.addAttribute("orders", orders);
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
            order.setCreatedAt(new java.sql.Date(today.getTime()));
            
            orderService.insert(order);

            // POST/REDIRECT/GET
            return "redirect:/customers/list";
            //return "redirect:/customers/" + customer.getCustomerId();
        }
    }
}
