package com.spring.aurora.entity;

import java.sql.Timestamp;

import com.spring.aurora.model.Container;
import com.spring.aurora.model.Debt;
import com.spring.aurora.model.Expense;
import com.spring.aurora.model.Order;
import com.spring.aurora.model.Payment;

public class DailySalesEntity {

	private Order order;
	private Container container;
	private Debt debt;
	private Payment payment;
	private Expense expense;
	private String customerName;
	private Double expenseAmount;
	private Double paidCash;
	private Double paidCheck;
	private Double balanceAmount;
	private String remarks;
	private String dateAndTime;
	private String returnedRound;
	private String returnedSlim;
	
	public String getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Container getContainer() {
		return container;
	}
	public void setContainer(Container container) {
		this.container = container;
	}
	public Debt getDebt() {
		return debt;
	}
	public void setDebt(Debt debt) {
		this.debt = debt;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	public Double getExpenseAmount() {
		return expenseAmount;
	}
	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	public Double getPaidCash() {
		return paidCash;
	}
	public void setPaidCash(Double paidCash) {
		this.paidCash = paidCash;
	}
	public Double getPaidCheck() {
		return paidCheck;
	}
	public void setPaidCheck(Double paidCheck) {
		this.paidCheck = paidCheck;
	}
	public Double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getReturnedRound() {
		return returnedRound;
	}
	public void setReturnedRound(String returnedRound) {
		this.returnedRound = returnedRound;
	}
	public String getReturnedSlim() {
		return returnedSlim;
	}
	public void setReturnedSlim(String returnedSlim) {
		this.returnedSlim = returnedSlim;
	}
	
}
