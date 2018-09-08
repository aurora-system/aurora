package com.spring.aurora.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.aurora.model.Payment;

@Repository
public class PaymentDaoImpl implements PaymentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Payment insert(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        session.save(payment);
        session.flush();
		return payment;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Payment> findAllByCustomerId(String customerId) {
        Session session = sessionFactory.getCurrentSession();
        List<Payment> payments = session.createCriteria(Payment.class)
                .add(Restrictions.eq("customerId", customerId)).list();
        return payments;
    }

    @Override
    public List<Payment> findAllByCustomerIdAndDate(String customerId, Date date) {
        return new ArrayList<>();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> findAllByDate(Date date) {
		 Session session = sessionFactory.getCurrentSession();
	        List<Payment> payments = session.createCriteria(Payment.class)
	                .add(Restrictions.eq("createdAt", date)).list();
	        return payments;
	}

    @SuppressWarnings("unchecked")
	@Override
    public List<Payment> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Payment> payments = session.createCriteria(Payment.class).list();
        return payments;
    }
}
