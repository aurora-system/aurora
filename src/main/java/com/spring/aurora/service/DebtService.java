package com.spring.aurora.service;

import com.spring.aurora.model.Debt;

import java.util.List;

public interface DebtService {
    Debt insert(Debt customer);
    List<Debt> findAllByCustomerId(int customerId);
}
