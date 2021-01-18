package com.spring.aurora.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CUSTOMER")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private long customerId;

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

    public boolean isNew() {
        return (this.customerId == 0);
    }
}
