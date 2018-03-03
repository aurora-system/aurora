package com.spring.aurora.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="PAYMENT")
public class Payment {

	@Id
    @Column(name="payment_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String paymentId;
	
	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="amount")
	private Double amount;

	@Column(name="payment_type")
	private String paymentType;

	@Column(name="remarks")
	private String remarks;
	
	@Column(name="created_at")
	private Date createdAt;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String type) {
		this.paymentType = type;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
