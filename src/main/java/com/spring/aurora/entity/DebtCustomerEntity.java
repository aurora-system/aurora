package com.spring.aurora.entity;

public class DebtCustomerEntity {
    private String customerName;
    private double debtsTotal;

    public DebtCustomerEntity(String name, double debtsTotal) {
        this.customerName = name;
        this.debtsTotal = debtsTotal;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDebtsTotal() {
        return debtsTotal;
    }

    public void setDebtsTotal(double debtsTotal) {
        this.debtsTotal = debtsTotal;
    }
}
