package com.spring.aurora.service;

import com.spring.aurora.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer insert(Customer customer);
    Customer update(Customer customer);
    Customer find(Customer customer);
    Customer view(String customerId);
    List<Customer> find(List<String> customerIdList);
    List<Customer> findAll();
}
