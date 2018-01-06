package com.spring.aurora.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.aurora.model.Order;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
    @Override
    public Order insert(Order order) {
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public List<Order> findAllByCustomerId(int customerId) {
        return null;
    }

    @Override
    public List<Order> findAllByDeliveryReceiptNumber(int drNumber) {
        return null;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAll() {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Order> orders = new ArrayList<>();
		
		orders = session.createQuery("select o from Order o").list();
		
		return orders;
	}
}
