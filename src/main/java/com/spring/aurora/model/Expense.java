package com.spring.aurora.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="EXPENSE")
public class Expense {

    @Id
    @Column(name="expense_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String expenseId;

    @Column(name="description")
    private String description;

    @Column(name="amount")
    private Double amount;

    @Column(name="created_at")
    //@Convert(converter = com.spring.aurora.util.LocalDateAttributeConverter.class)
    private Date created_at;

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
