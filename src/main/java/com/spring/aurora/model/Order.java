package com.spring.aurora.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="ORDERS")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private long orderId;

    @Column(name="customer_id")
    private long customerId;

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

    public boolean isNew() {
        return (this.orderId == 0);
    }
}