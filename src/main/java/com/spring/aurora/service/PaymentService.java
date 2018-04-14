package com.spring.aurora.service;

import com.spring.aurora.model.Payment;

import java.sql.Date;
import java.util.List;

public interface PaymentService {
    Payment insert(Payment payment);
    List<Payment> findAllByCustomerId(String customerId);
    List<Payment> findAllByCustomerIdAndDate(String customerId, Date date);
    List<Payment> findAllByDate(Date date);
    double getPaymentsTotalByCustomerId(String customerId);
    double getTotalPayments();
}
