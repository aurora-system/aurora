package com.spring.aurora.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.aurora.model.Debt;

@Repository
public class DebtDaoImpl implements DebtDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Debt insert(Debt debt) {
        Session session = sessionFactory.getCurrentSession();
        session.save(debt);
        session.flush();
		return debt;
    }
    
    @Override
    public Debt delete(Debt debt) {
    	
    	Session session = this.sessionFactory.getCurrentSession();

		// TODO: set the Safe Updates mode in MySQL to false (Edit > Preferences > SQL Editor > un-check Safe Updates
		session.createQuery("delete from Debt d where d.customerId = :customerId and d.orderId = :orderId")
				.setParameter("customerId", debt.getCustomerId()).setParameter("orderId", debt.getOrderId())
				.executeUpdate();

		session.flush();
		return null;
    }

    @Override
    public List<Debt> findAllByCustomerId(String customerId) {
        Session session = sessionFactory.getCurrentSession();
        List<Debt> debts = session.createCriteria(Debt.class)
                .add(Restrictions.eq("customerId", customerId)).list();
        return debts;
    }

    @Override
    public List<Debt> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Debt> debts = session.createCriteria(Debt.class).list();
        return debts;
    }
}
