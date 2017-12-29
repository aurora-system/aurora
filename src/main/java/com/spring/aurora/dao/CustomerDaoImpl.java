package com.spring.aurora.dao;

import com.spring.aurora.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Customer insert(Customer customer) {
        customer.setCustomerId("1");
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        return customer;
    }

    @Override
    public Customer find(Customer customer) {
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Customer c = new Customer();
            c.setCustomerId(String.valueOf(i));
            c.setType(i%2==0?"Walk-in":"Delivery");
            c.setName("Customer"+i);
            c.setAddress("Address"+i*11);
            c.setEmailAddress("customer."+i+"@email.com");
            c.setMainNumber("0917765432"+i);
            c.setContactName("Anyone");
            c.setAlternateNumber("0918"+i+"234567");
            customers.add(c);
        }
        return customers;
    }
}
