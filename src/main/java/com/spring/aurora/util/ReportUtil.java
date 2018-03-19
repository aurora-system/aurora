package com.spring.aurora.util;

import java.util.List;

import com.spring.aurora.model.Expense;
import com.spring.aurora.model.Order;
import com.spring.aurora.model.Payment;

public class ReportUtil {

	public static Double getExpenseTotal (List<Expense> expenseList) {
		
		Double expenseTotal = 0.0;
		
		for (Expense e : expenseList) {
			expenseTotal += e.getAmount();
		}
		
		return expenseTotal;
	}
	
	public static Double getPaymentTotal (List<Payment> paymentList) {

		Double paymentTotal = 0.0;
		
		for (Payment p : paymentList) {
			paymentTotal += p.getAmount();
		}
		
		return paymentTotal;
	}
	
	public static int getRoundDeliveredTotal (List<Order> orderList) {
		
		int roundTotal = 0;
		
		for (Order o : orderList) {
			roundTotal += o.getRoundCount();
		}
		
		return roundTotal;
	}
	
	public static int getSlimDeliveredTotal (List<Order> orderList) {
		
		int slimTotal = 0;
		
		for (Order o : orderList) {
			slimTotal += o.getRoundCount();
		}
		
		return slimTotal;
	}
	
	public static boolean isLeapYear(int year) {

		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String applyCurrencyFormat (String value) {
		
		System.out.println("Original value: " + value);
		String[] splitValue = value.split("\\.");
		
		StringBuffer formattedStr = new StringBuffer();
		System.out.println("0: " + splitValue[0]);
		System.out.println("1: " + splitValue[1]);
		System.out.println("Length: " + splitValue.length);
		if (splitValue.length == 2) {
			
			formattedStr.append(splitValue[0]);
			if (splitValue[1].length() == 2) {
				return value;
			} else if (splitValue[1].length() == 1) {
				formattedStr.append(".");
				formattedStr.append(splitValue[1]);
				formattedStr.append("0");
			}
		} else {
			formattedStr.append(".00");
		}
		
		System.out.println("Formatted: " + formattedStr.toString());
		return formattedStr.toString();
	}
}
