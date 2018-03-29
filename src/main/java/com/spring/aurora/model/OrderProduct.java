package com.spring.aurora.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_PRODUCT")
public class OrderProduct {

	@Id
    @Column(name="order_product_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String orderProductId;
	
	@Column(name="order_id")
	private String orderId;
	
	@Column(name="product_id")
	private String productId;
	
	@Column(name="quantity")
	private String quantity;

	public String getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(String orderProductId) {
		this.orderProductId = orderProductId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
}
