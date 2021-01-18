package com.spring.aurora.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="DEBT")
@Data
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="debt_id")
    private long debtId;

    @Column(name="customer_id")
    private long customerId;

    @Column(name="amount")
    private Double amount;

    @Column(name="remarks")
    private String remarks;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="linked_order_id")
    private long orderId;
}
