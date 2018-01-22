package com.spring.aurora.dao;

import com.spring.aurora.model.Debt;
import com.spring.aurora.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DebtDaoImpl implements DebtDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Debt insert(Debt debt) {
        Session session = sessionFactory.getCurrentSession();
        session.save(debt);
        return debt;
    }

    @Override
    public List<Debt> findAllByCustomerId(String customerId) {
        Session session = sessionFactory.getCurrentSession();
        List<Debt> debts = session.createCriteria(Debt.class)
                .add(Restrictions.eq("customerId", customerId)).list();
        return debts;
    }
}
