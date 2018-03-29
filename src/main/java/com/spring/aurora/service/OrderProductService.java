package com.spring.aurora.service;

import java.util.List;

import com.spring.aurora.model.OrderProduct;

public interface OrderProductService {
	OrderProduct insert(OrderProduct orderProduct);
	List<OrderProduct> findAllByOrderId(String orderId);
}
