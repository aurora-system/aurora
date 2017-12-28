package com.spring.aurora.dao;

import com.spring.aurora.model.Customer;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Customer insert(Customer customer) {
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public Customer find(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }
}
