package com.spring.aurora.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.aurora.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

	private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Order insert(Order order) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("Order ID:" + order.getOrderId());
		session.save(order);
		session.flush();
		return order;
	}

	@Override
	public Order update(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(order);
		session.getTransaction().commit();
		return order;
	}

	@Override
    public Order delete(Order order) {
    	
    	Session session = this.sessionFactory.getCurrentSession();

		// TODO: set the Safe Updates mode in MySQL to false (Edit > Preferences > SQL Editor > un-check Safe Updates
		session.createQuery("delete from Order o where o.orderId = :orderId").setParameter("orderId", order.getOrderId())
				.executeUpdate();

		session.flush();
		return null;
    }
	
	@Override
	public void setToDelivered(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(
				"update Order o set o.status = 'Delivered', o.createdAt = :createdAt where o.orderId = :orderId")
				.setParameter("orderId", orderId).setParameter("createdAt", Timestamp.valueOf(LocalDateTime.now()))
				.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllByCustomerId(String customerId) {

		Session session = this.sessionFactory.getCurrentSession();

		List<Order> orders = new ArrayList<>();

		orders = session.createQuery("select o from Order o where o.customerId = :customerId")
				.setParameter("customerId", customerId).list();

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

	@SuppressWarnings("unchecked")
	@Override
	public Timestamp getMostRecentOrderDate(String customerId) {

		Session session = this.sessionFactory.getCurrentSession();

		List<Order> orderList = session
				.createQuery("select o from Order o where o.customerId = :customerId order by createdAt desc")
				.setParameter("customerId", customerId).list();

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllPendingOrders() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orders = session.createCriteria(Order.class)
				.add(Restrictions.eq("status", "Pending"))
				.list();
		return orders;
	}

	@Override
	public void cancelOrder(String orderId) {
		Session session = this.sessionFactory.getCurrentSession();

		session.createQuery(
				"update Order o set o.status = 'Cancelled' where o.orderId = :orderId")
				.setParameter("orderId", orderId).executeUpdate();
		
//		Query queryForDebt = session.createQuery("delete from Debt d where d.orderId = :orderId");
//		queryForDebt.setParameter("orderId", order.getOrderId());
//		queryForDebt.executeUpdate();
//
//		Query queryForOrder = session.createQuery("delete from Order o where o.orderId = :orderId");
//		queryForOrder.setParameter("orderId", order.getOrderId());
//		queryForOrder.executeUpdate();
	}

	@Override
	public Order findOrderByOrderId(String orderId) {
		Session session = this.sessionFactory.getCurrentSession();

		Order order = new Order();

		order = (Order) session.createQuery("select o from Order o where o.orderId = :orderId")
				.setParameter("orderId", orderId).list().get(0);

		return order;
	}

	@Override
	public String getNewDrNumber() {

		//Session session = this.sessionFactory.getCurrentSession();

		String newDrNumber = "";

		List<Order> orderList = this.findAll();
		List<Integer> drList = new ArrayList<>();

		for (Order order : orderList) {
			drList.add(Integer.valueOf(order.getDeliveryReceiptNum()));
		}

		if (drList == null || drList.size() == 0) {
			newDrNumber = "1";
		} else {
			Integer temp = Collections.max(drList) + 1;
			newDrNumber = temp.toString();

			System.out.println("New DR Number is: " + newDrNumber);
		}
		
		return newDrNumber;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllOrdersPerMonth(String month, String year) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Order> ordersForTheMonth = new ArrayList<>();
		
		ordersForTheMonth = session
				.createQuery("select o from Order o where MONTH(o.createdAt) = :month and YEAR(o.createdAt) = :year")
				.setParameter("month", Integer.parseInt(month)).setParameter("year", Integer.parseInt(year)).list();
		
		return ordersForTheMonth;
	}
}
