package com.spring.aurora.service;

import com.spring.aurora.model.Debt;

import java.sql.Date;
import java.util.List;

public interface DebtService {
    Debt insert(Debt customer);
    List<Debt> findAllByCustomerId(String customerId);
    List<Debt> findAllByCustomerIdAndDate(String customerId, Date date);
    double findDebtsTotalByCustomerId(String customerId);
    double findTotalARs();
}
