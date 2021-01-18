package com.spring.aurora.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.spring.aurora.model.Order;

public interface OrderService {
    Order insert(Order order);
    Order update(Order order);
    Order delete(Order order);

    Timestamp getMostRecentOrderDate(long customerId);

    List<Order> findAllByCustomerId(long customerId);
    List<Order> findAllOrdersToday(Date dateParam);
    List<Order> findAllByDeliveryReceiptNumber(int drNumber);
    List<Order> findAll();
    List<Order> findAllOrdersPerMonth(String month, String year);

    Order findOrderByOrderId(long orderId);

    void cancelOrder(long orderId);

    void setToDelivered(long orderId);
    String getNewDrNumber();
}
