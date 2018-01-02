package com.spring.aurora.dao;

import com.spring.aurora.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
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

    @SuppressWarnings("unchecked")
	@Override
	@Transactional
    public List<Customer> findAll() {
    	
    	Session session = this.sessionFactory.getCurrentSession();
        
    	List<Customer> customers = new ArrayList<>();
        
        customers = session.createQuery("select c from Customer c").list();
        
        return customers;
    }
}
