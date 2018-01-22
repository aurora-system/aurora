package com.spring.aurora.dao;

import com.spring.aurora.model.Debt;

import java.util.List;

public interface DebtDao {
    Debt insert(Debt debt);
    List<Debt> findAllByCustomerId(String customerId);
}
