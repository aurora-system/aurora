package com.spring.aurora.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer {

	@Id
    @Column(name="customer_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String customerId;
	
	@Column(name="type")
	private String type;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="contact_name")
	private String contactName;
	
	@Column(name="main_number")
	private String mainNumber;
	
	@Column(name="alternate_number")
	private String alternateNumber;
	
	@Column(name="email_address")
	private String emailAddress;
	
	@Column(name="order_interval")
	private int orderInterval;
	
	@Column(name="total_round")
	private int totalRound;
	
	@Column(name="total_slim")
	private int totalSlim;
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMainNumber() {
		return mainNumber;
	}

	public void setMainNumber(String mainNumber) {
		this.mainNumber = mainNumber;
	}

	public String getAlternateNumber() {
		return alternateNumber;
	}

	public void setAlternateNumber(String alternateNumber) {
		this.alternateNumber = alternateNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public int getOrderInterval() {
		return orderInterval;
	}

	public void setOrderInterval(int orderInterval) {
		this.orderInterval = orderInterval;
	}

	public int getTotalRound() {
		return totalRound;
	}

	public void setTotalRound(int totalRound) {
		this.totalRound = totalRound;
	}

	public int getTotalSlim() {
		return totalSlim;
	}

	public void setTotalSlim(int totalSlim) {
		this.totalSlim = totalSlim;
	}

	public boolean isNew() {
		return (this.customerId == null || this.customerId.isEmpty());
	}
}
