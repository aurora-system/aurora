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
@Transactional
public class CustomerDaoImpl implements CustomerDao {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
    
    @Override
    public Customer insert(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        logger.debug("CustomerName:" + customer.getName());
        session.save(customer);
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.update(customer);
        return customer;
    }

    @Override
    public Customer find(Customer customer) {
        return customer;
    }

    @SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
    	
    	Session session = this.sessionFactory.getCurrentSession();
        
    	List<Customer> customers = new ArrayList<>();
        
        customers = session.createQuery("select c from Customer c").list();
        
        return customers;
    }

	@Override
	public Customer view(String customerId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		Customer customer = new Customer();
		
		customer = (Customer) session.createQuery("select c from Customer c where c.customerId = :customerId").setParameter("customerId", customerId).list().get(0);
		
		return customer;
	}
}
