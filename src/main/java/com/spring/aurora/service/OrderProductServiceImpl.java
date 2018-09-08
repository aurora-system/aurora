package com.spring.aurora.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.aurora.dao.OrderProductDao;
import com.spring.aurora.model.OrderProduct;

@Service("orderProductService")
@Transactional
public class OrderProductServiceImpl implements OrderProductService{

	@Autowired
	private OrderProductDao orderProductDao;
	
	public void setOrderProductDao(OrderProductDao orderProductDao) {
		this.orderProductDao = orderProductDao;
	}

	@Override
	public OrderProduct insert(OrderProduct orderProduct) {
		return orderProductDao.insert(orderProduct);
	}
	
	@Override
	public OrderProduct update(OrderProduct orderProduct) {
		return orderProductDao.update(orderProduct);
	}

	@Override
	public List<OrderProduct> findAllByOrderId(String orderId) {
		return orderProductDao.findAllByOrderId(orderId);
	}

	@Override
	public void remove(OrderProduct orderProduct) {
		orderProductDao.remove(orderProduct);
	}

}
