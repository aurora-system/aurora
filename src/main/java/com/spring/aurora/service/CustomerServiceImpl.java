package com.spring.aurora.service;

import com.spring.aurora.dao.*;
import com.spring.aurora.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    private OrderDao orderDao;
    private ContainerDao containerDao;
    private DebtDao debtDao;
    private PaymentDao paymentDao;

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public ContainerDao getContainerDao() {
        return containerDao;
    }

    public void setContainerDao(ContainerDao containerDao) {
        this.containerDao = containerDao;
    }

    public DebtDao getDebtDao() {
        return debtDao;
    }

    public void setDebtDao(DebtDao debtDao) {
        this.debtDao = debtDao;
    }

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public Customer insert(Customer customer) {
        return customerDao.insert(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerDao.update(customer);
    }

    @Override
    public Customer find(Customer customer) {
        return customerDao.find(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
