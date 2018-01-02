package com.spring.aurora.dao;

import com.spring.aurora.model.Expense;
import jdk.nashorn.internal.ir.ExpressionStatement;

import java.sql.Date;
import java.util.List;

public interface ExpenseDao {
    Expense insert(Expense expense);
    List<Expense> findAllByDate(Date date);
}
