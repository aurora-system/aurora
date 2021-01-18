package com.spring.aurora.service;

import java.util.List;

import com.spring.aurora.model.Customer;

public interface CustomerService {
    Customer insert(Customer customer);
    Customer update(Customer customer);
    Customer find(Customer customer);

    Customer view(long customerId);

    List<Customer> findAllByCustomerIdIn(List<Long> customerIdList);
    List<Customer> findAll();
}
