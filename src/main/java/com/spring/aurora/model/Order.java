package com.spring.aurora.model;

import java.sql.Timestamp;
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
	
	@Column(name="slim_refill_only_count")
	private int slimRefillOnlyCount;
	
	@Column(name="round_refill_only_count")
	private int roundRefillOnlyCount;
	
	@Column(name="slim_container_only_count")
	private int slimContainerOnlyCount;
	
	@Column(name="round_container_only_count")
	private int roundContainerOnlyCount;
	
	@Column(name="slim_refill_with_container_count")
	private int slimRefillWithContainerCount;
	
	@Column(name="round_refill_with_container_count")
	private int roundRefillWithContainerCount;
	
	@Column(name="round_free_count")
	private int roundFreeCount;
	
	@Column(name="slim_free_count")
	private int slimFreeCount;
	
	@Column(name="cont_slim_returned")
	private String slimReturned;
	
	@Column(name="cont_round_returned")
	private String roundReturned;
	
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

	public int getSlimRefillOnlyCount() {
		return slimRefillOnlyCount;
	}

	public void setSlimRefillOnlyCount(int slimCount) {
		this.slimRefillOnlyCount = slimCount;
	}

	public int getRoundRefillOnlyCount() {
		return roundRefillOnlyCount;
	}

	public void setRoundRefillOnlyCount(int roundCount) {
		this.roundRefillOnlyCount = roundCount;
	}

	public int getSlimContainerOnlyCount() {
		return slimContainerOnlyCount;
	}

	public void setSlimContainerOnlyCount(int slimContainerOnlyCount) {
		this.slimContainerOnlyCount = slimContainerOnlyCount;
	}

	public int getRoundContainerOnlyCount() {
		return roundContainerOnlyCount;
	}

	public void setRoundContainerOnlyCount(int roundContainerOnlyCount) {
		this.roundContainerOnlyCount = roundContainerOnlyCount;
	}

	public int getSlimRefillWithContainerCount() {
		return slimRefillWithContainerCount;
	}

	public void setSlimRefillWithContainerCount(int slimRefillWithContainerCount) {
		this.slimRefillWithContainerCount = slimRefillWithContainerCount;
	}

	public int getRoundRefillWithContainerCount() {
		return roundRefillWithContainerCount;
	}

	public void setRoundRefillWithContainerCount(int roundRefillWithContainerCount) {
		this.roundRefillWithContainerCount = roundRefillWithContainerCount;
	}

	public int getRoundFreeCount() {
		return roundFreeCount;
	}

	public void setRoundFreeCount(int roundFreeCount) {
		this.roundFreeCount = roundFreeCount;
	}

	public int getSlimFreeCount() {
		return slimFreeCount;
	}

	public void setSlimFreeCount(int slimFreeCount) {
		this.slimFreeCount = slimFreeCount;
	}

	public String getSlimReturned() {
		return slimReturned;
	}

	public void setSlimReturned(String slimReturned) {
		this.slimReturned = slimReturned;
	}

	public String getRoundReturned() {
		return roundReturned;
	}

	public void setRoundReturned(String roundReturned) {
		this.roundReturned = roundReturned;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isNew() {
		return (this.orderId == null || this.orderId.isEmpty());
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
	        return false;
	    }
		
	    if (!Order.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    
	    final Order other = (Order) obj;
	    if (!this.orderId.equals(other.orderId)) {
	        return false;
	    }

	    return true;
	}
}
