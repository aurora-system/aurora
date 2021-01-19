package com.spring.aurora.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.aurora.model.Order;

public interface OrderDao extends CrudRepository<Order, Long> {
    List<Order> findAllByCustomerId(long customerId);

    @Query("select o from Order o where DATE(o.createdAt) = :timestamp")
    List<Order> findAllOrdersToday(Date timestamp);

    @Query("select o from Order o where status = 'Pending'")
    List<Order> findAllPendingOrders();

    @Query("select o from Order o where MONTH(o.createdAt) = :month and YEAR(o.createdAt) = :year")
    List<Order> findAllOrdersPerMonth(Integer month, Integer year);

    @Query("select o from Order o where o.customerId = :customerId order by createdAt desc")
    Timestamp getMostRecentOrderDate(long customerId);

    @Modifying
    @Query("update Order o set o.status = 'Cancelled' where o.orderId = :orderId")
    void cancelOrder(long orderId);

    @Modifying
    @Query("update Order o set o.status = 'Delivered', o.createdAt = now() where o.orderId = :orderId")
    void setToDelivered(long orderId);

    // List<Order> findAllByDeliveryReceiptNumber(int drNumber);
    // Order findOrderByOrderId(long orderId);
    // String getNewDrNumber();
}
