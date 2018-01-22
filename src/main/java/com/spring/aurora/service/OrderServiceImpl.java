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

import java.util.List;

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
}
