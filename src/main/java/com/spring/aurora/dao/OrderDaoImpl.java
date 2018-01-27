package com.spring.aurora.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    
    @Override
    public void setToDelivered(String orderId) {
    	Session session = sessionFactory.getCurrentSession();
        session.createQuery("update Order o set o.status = 'Delivered' where o.orderId = :orderId").setParameter("orderId", orderId).executeUpdate();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllOrdersToday(Date dateParam) {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orderList = session.createQuery("select o from Order o where DATE(o.createdAt) = :timestamp")
				.setParameter("timestamp", dateParam).list();

        return orderList;
	}

	@Override
	public void cancelOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Query queryForDebt = session.createQuery("delete from Debt d where d.orderId = :orderId");
		queryForDebt.setParameter("orderId", order.getOrderId());
		queryForDebt.executeUpdate();
		
		Query queryForOrder = session.createQuery("delete from Order o where o.orderId = :orderId");
		queryForOrder.setParameter("orderId", order.getOrderId());
		queryForOrder.executeUpdate();
	}

	@Override
	public Order findOrderByOrderId(String orderId) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Order order = new Order();
		
		order = (Order) session.createQuery("select o from Order o where o.orderId = :orderId").setParameter("orderId", orderId).list().get(0);
		
		return order;
	}
}
