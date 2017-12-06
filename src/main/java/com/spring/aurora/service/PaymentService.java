package com.spring.aurora.service;

import com.spring.aurora.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment insert(Payment payment);
    List<Payment> findAllByCustomerId(int customerId);
}
