package com.spring.aurora.entity;

import java.util.List;

import com.spring.aurora.model.Order;
import com.spring.aurora.model.OrderProduct;
import com.spring.aurora.model.Product;

public class OrderProductEntity {

	private Order order;
	private List<OrderProduct> opList;
	private String saveReturned;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderProduct> getOpList() {
		return opList;
	}
	public void setOpList(List<OrderProduct> opList) {
		this.opList = opList;
	}
	public String getSaveReturned() {
		return saveReturned;
	}
	public void setSaveReturned(String saveReturned) {
		this.saveReturned = saveReturned;
	}
	
}
