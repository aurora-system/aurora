package com.spring.aurora.dao;

import com.spring.aurora.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PaymentDaoImpl implements PaymentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Payment insert(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        session.save(payment);
        return payment;
    }

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

	@Override
	public List<Payment> findAllByDate(Date date) {
		 Session session = sessionFactory.getCurrentSession();
	        List<Payment> payments = session.createCriteria(Payment.class)
	                .add(Restrictions.eq("createdAt", date)).list();
	        return payments;
	}
}
