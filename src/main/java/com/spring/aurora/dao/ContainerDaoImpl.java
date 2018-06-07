package com.spring.aurora.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.aurora.model.Container;
import com.spring.aurora.model.Debt;
import com.spring.aurora.model.Order;

@Repository
@Transactional
public class ContainerDaoImpl implements ContainerDao {

	private static final Logger logger = LoggerFactory.getLogger(ContainerDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Container insert(Container container) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("Container activity inserted.");
		session.save(container);
		return container;
	}
	
	@Override
	public Container update(Container container) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(container);
		return container;
	}
	
	@Override
	public Container delete(Container container) {

		Session session = this.sessionFactory.getCurrentSession();

		// TODO: set the Safe Updates mode in MySQL to false (Edit > Preferences > SQL Editor > un-check Safe Updates
		session.createQuery("delete from Container c where c.customerId = :customerId and c.orderId = :orderId")
				.setParameter("customerId", container.getCustomerId()).setParameter("orderId", container.getOrderId())
				.executeUpdate();

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Container> findAllByCustomerId(String customerId) {

		Session session = this.sessionFactory.getCurrentSession();

		List<Container> containers = new ArrayList<>();

		containers = session.createQuery("select c from Container c where c.customerId = :customerId")
				.setParameter("customerId", customerId).list();

		return containers;
	}

	@Override
	public List<Container> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Container> containers = session.createCriteria(Container.class).list();
		return containers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Container> findContainerActivityByDate(Date date) {

		Session session = this.sessionFactory.getCurrentSession();

		List<Container> containers = new ArrayList<>();

		containers = session.createQuery("select c from Container c where DATE(c.createdAt) = :timestamp")
				.setParameter("timestamp", date).list();
		
		return containers;
	}

}
