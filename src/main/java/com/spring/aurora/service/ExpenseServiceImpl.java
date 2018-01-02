package com.spring.aurora.service;

import com.spring.aurora.dao.ExpenseDao;
import com.spring.aurora.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service("expenseService")
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    public void setExpenseDao(ExpenseDao dao) {
        this.expenseDao = dao;
    }

    @Override
    public Expense save(Expense expense) {
        return expenseDao.insert(expense);
    }

    @Override
    public List<Expense> findAllByDate(Date date) {
        return expenseDao.findAllByDate(date);
    }
}