package com.spring.aurora.entity;

import com.spring.aurora.model.Order;

public class OrderCustomerEntity {

	private Order order;
	private String CustomerName;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	
}
