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
import com.spring.aurora.model.OrderProduct;

@Repository
@Transactional
public class OrderProductDaoImpl implements OrderProductDao {

	private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public OrderProduct insert(OrderProduct orderProduct) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("Order ID:" + orderProduct.getOrderId());
		logger.debug("Product ID:" + orderProduct.getProductId());
		session.save(orderProduct);
		return orderProduct;
	}
	
	@Override
	public OrderProduct update(OrderProduct orderProduct) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(orderProduct);
		return orderProduct;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderProduct> findAllByOrderId(String orderId) {
		Session session = this.sessionFactory.getCurrentSession();

		List<OrderProduct> orderProducts = new ArrayList<>();

		orderProducts = session.createQuery("select op from OrderProduct op where op.orderId = :orderId")
				.setParameter("orderId", orderId).list();

		return orderProducts;
	}

	@Override
	public void remove(OrderProduct orderProduct) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(orderProduct);
	}

}
