package com.spring.aurora.dao;

import com.spring.aurora.model.Payment;

import java.sql.Date;
import java.util.List;

public interface PaymentDao {
    Payment insert(Payment payment);
    List<Payment> findAllByCustomerId(String customerId);
    List<Payment> findAllByCustomerIdAndDate(String customerId, Date date);
    List<Payment> findAllByDate(Date date);
}
