package com.spring.aurora.dao;

import com.spring.aurora.model.Order;

import java.util.List;

public interface OrderDao {
    Order insert(Order order);
    Order update(Order order);
    List<Order> findAllByCustomerId(int customerId);
    List<Order> findAllByDeliveryReceiptNumber(int drNumber);
    List<Order> findAll();
}
