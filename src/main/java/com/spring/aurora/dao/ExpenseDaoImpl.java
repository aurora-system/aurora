package com.spring.aurora.dao;

import com.spring.aurora.model.Expense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ExpenseDaoImpl implements ExpenseDao {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Expense insert(Expense expense) {
        Session session = sessionFactory.getCurrentSession();
        session.save(expense);
        return expense;
    }

    @Override
    public List<Expense> findAllByDate(Date date) {
        Session session = sessionFactory.getCurrentSession();
        List<Expense> expenses = session.createCriteria(Expense.class)
                .add(Restrictions.eq("created_at", date)).list();
        return expenses;
    }
}
