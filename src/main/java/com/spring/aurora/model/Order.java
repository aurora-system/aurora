package com.spring.aurora.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {

	@Id
    @Column(name="order_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String orderId;
	
	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="delivery_receipt_num")
	private String deliveryReceiptNum;
	
	@Column(name="status")
	private String status;
	
	@Column(name="amount_paid")
	private Double amountPaid;
	
	@Column(name="total_amount")
	private Double totalAmount;
	
	@Column(name="cont_slim_count")
	private int slimCount;
	
	@Column(name="cont_round_count")
	private int roundCount;
	
	@Column(name="cont_slim_returned")
	private int slimReturned;
	
	@Column(name="cont_round_returned")
	private int roundReturned;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="remarks")
	private String remarks;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDeliveryReceiptNum() {
		return deliveryReceiptNum;
	}

	public void setDeliveryReceiptNum(String deliveryReceiptNum) {
		this.deliveryReceiptNum = deliveryReceiptNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getSlimCount() {
		return slimCount;
	}

	public void setSlimCount(int slimCount) {
		this.slimCount = slimCount;
	}

	public int getRoundCount() {
		return roundCount;
	}

	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}

	public int getSlimReturned() {
		return slimReturned;
	}

	public void setSlimReturned(int slimReturned) {
		this.slimReturned = slimReturned;
	}

	public int getRoundReturned() {
		return roundReturned;
	}

	public void setRoundReturned(int roundReturned) {
		this.roundReturned = roundReturned;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isNew() {
		return (this.orderId == null);
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
