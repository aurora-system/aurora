package com.spring.aurora.controller;

import com.spring.aurora.model.Order;
import com.spring.aurora.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

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
}
