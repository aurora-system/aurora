package com.spring.aurora.dao;

import java.sql.Timestamp;
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
    	Session session = sessionFactory.getCurrentSession();
        logger.debug("Order ID:" + order.getOrderId());
        session.save(order);
        return order;
    }

    @Override
    public Order update(Order order) {
    	Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(order);
        return order;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Order> findAllByCustomerId(String customerId) {
        
    	Session session = this.sessionFactory.getCurrentSession();
		
		List<Order> orders = new ArrayList<>();
    	
		orders = session.createQuery("select o from Order o where o.customerId = :customerId").setParameter("customerId", customerId).list();
		
		return orders;
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

	@Override
	public Timestamp getMostRecentOrderDate(String customerId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Order> orderList = session.createQuery("select o from Order o where o.customerId = :customerId order by createdAt desc").setParameter("customerId", customerId).list();
		
		Order latestOrder = null;
		
		if (orderList != null && orderList.size() > 0) {
			latestOrder = orderList.get(0);
			return latestOrder.getCreatedAt();
		} else {
			return null;
		}
		
	}
}
