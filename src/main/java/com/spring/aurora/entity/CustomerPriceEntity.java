package com.spring.aurora.entity;

import com.spring.aurora.model.Customer;

public class CustomerPriceEntity {
    
	private Customer customer;
    private double refillPrice;
	
    public CustomerPriceEntity(Customer customer, double refillPrice) {
		super();
		this.customer = customer;
		this.refillPrice = refillPrice;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getRefillPrice() {
		return refillPrice;
	}
	public void setRefillPrice(double refillPrice) {
		this.refillPrice = refillPrice;
	}

   
}
