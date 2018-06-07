package com.spring.aurora.dao;

import com.spring.aurora.model.Debt;

import java.util.List;

public interface DebtDao {
    Debt insert(Debt debt);
    Debt delete(Debt debt);
    List<Debt> findAllByCustomerId(String customerId);
    List<Debt> findAll();
}
