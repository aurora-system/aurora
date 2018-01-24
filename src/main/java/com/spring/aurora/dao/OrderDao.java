package com.spring.aurora.dao;

import com.spring.aurora.model.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDao {
    Order insert(Order order);
    Order update(Order order);
    Timestamp getMostRecentOrderDate(String customerId);
    List<Order> findAllByCustomerId(String customerId);
    List<Order> findAllByDeliveryReceiptNumber(int drNumber);
    List<Order> findAll();
}
