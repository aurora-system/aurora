package com.spring.aurora.service;

import com.spring.aurora.model.Order;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    Order insert(Order order);
    Order update(Order order);
    Timestamp getMostRecentOrderDate(String customerId);
    List<Order> findAllByCustomerId(String customerId);
    List<Order> findAllOrdersToday(Date dateParam);
    List<Order> findAllByDeliveryReceiptNumber(int drNumber);
    List<Order> findAll();
    Order findOrderByOrderId(String orderId);
    void cancelOrder(Order order);
    void setToDelivered(String orderId);
}
