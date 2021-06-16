package com.spring.aurora.service;

import java.sql.Date;
import java.util.List;

import com.spring.aurora.model.Debt;

public interface DebtService {
    Debt insert(Debt customer);
    void delete(Debt debt);

    List<Debt> findAllByCustomerId(long customerId);

    List<Debt> findAllByCustomerIdAndDate(long customerId, Date date);

    double findDebtsTotalByCustomerId(long customerId);
    double findTotalARs();
}
