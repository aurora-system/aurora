package com.spring.aurora.dao;

import java.util.List;

import com.spring.aurora.model.Order;
import com.spring.aurora.model.OrderProduct;

public interface OrderProductDao {
	
    OrderProduct insert(OrderProduct orderProduct);
    List<OrderProduct> findAllByOrderId(String orderId);
}
