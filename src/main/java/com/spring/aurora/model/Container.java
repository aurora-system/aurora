package com.spring.aurora.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONTAINER")
public class Container {

	@Id
	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="status")
	private String status;
	
	@Column(name="round_count")
	private int roundCount;
	
	@Column(name="slim_count")
	private int slimCount;
	
	@Column(name="date")
	private Timestamp date;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRoundCount() {
		return roundCount;
	}

	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}

	public int getSlimCount() {
		return slimCount;
	}

	public void setSlimCount(int slimCount) {
		this.slimCount = slimCount;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}
