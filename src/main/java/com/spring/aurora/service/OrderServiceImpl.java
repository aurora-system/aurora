package com.spring.aurora.service;

import com.spring.aurora.dao.ContainerDao;
import com.spring.aurora.dao.CustomerDao;
import com.spring.aurora.dao.DebtDao;
import com.spring.aurora.dao.OrderDao;
import com.spring.aurora.dao.PaymentDao;
import com.spring.aurora.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order insert(Order order) {
        return orderDao.insert(order);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public List<Order> findAllByCustomerId(String customerId) {
        return orderDao.findAllByCustomerId(customerId);
    }

    @Override
    public List<Order> findAllByDeliveryReceiptNumber(int drNumber) {
        return null;
    }

	@Override
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	@Override
	public Timestamp getMostRecentOrderDate(String customerId) {
		return orderDao.getMostRecentOrderDate(customerId);
	}

	@Override
	public List<Order> findAllOrdersToday(Date dateParam) {
		List<Order> pendingOrders = orderDao.findAllPendingOrders();
		List<Order> todayOrders = orderDao.findAllOrdersToday(dateParam);
		List<Order> allOrders = new ArrayList<>();
		allOrders.addAll(pendingOrders);
		
		for (Order todayOrder: todayOrders) {
			if (!allOrders.contains(todayOrder)) {
				allOrders.add(todayOrder);
			}
		}
		
		//allOrders.addAll(todayOrders);
		return allOrders;
	}

	@Override
	public void cancelOrder(String orderId) {
		orderDao.cancelOrder(orderId);
	}

	@Override
	public void setToDelivered(String orderId) {
		orderDao.setToDelivered(orderId);
	}

	@Override
	public Order findOrderByOrderId(String orderId) {
		return orderDao.findOrderByOrderId(orderId);
	}

	@Override
	public String getNewDrNumber() {
		return orderDao.getNewDrNumber();
	}
}
