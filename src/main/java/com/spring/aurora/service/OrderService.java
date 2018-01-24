package com.spring.aurora.service;

import com.spring.aurora.model.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    Order insert(Order order);
    Order update(Order order);
    Timestamp getMostRecentOrderDate(String customerId);
    List<Order> findAllByCustomerId(String customerId);
    List<Order> findAllByDeliveryReceiptNumber(int drNumber);
    List<Order> findAll();
}
