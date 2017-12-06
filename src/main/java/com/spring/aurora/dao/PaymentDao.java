package com.spring.aurora.dao;

import com.spring.aurora.model.Payment;

import java.util.List;

public interface PaymentDao {
    Payment insert(Payment payment);
    List<Payment> findAllByCustomerId(int customerId);
}
