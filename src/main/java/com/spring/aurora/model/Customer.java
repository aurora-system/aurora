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
}
