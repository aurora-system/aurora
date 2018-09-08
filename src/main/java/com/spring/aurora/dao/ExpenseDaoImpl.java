package com.spring.aurora.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.aurora.model.Expense;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Expense insert(Expense expense) {
        Session session = sessionFactory.getCurrentSession();
        session.save(expense);
        session.flush();
		logger.info("Expense="+expense);
        return expense;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Expense> findAllByDate(Date date) {
        Session session = sessionFactory.getCurrentSession();
        List<Expense> expenses = session.createCriteria(Expense.class)
                .add(Restrictions.eq("createdAt", date)).list();
        return expenses;
    }
}
