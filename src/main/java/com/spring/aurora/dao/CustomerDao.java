package com.spring.aurora.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.aurora.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, Long> {
    List<Customer> findAllByCustomerIdIn(List<Long> customerIds);
}
