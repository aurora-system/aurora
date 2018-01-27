package com.spring.aurora.service;

import com.spring.aurora.dao.PaymentDao;
import com.spring.aurora.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Payment insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public List<Payment> findAllByCustomerId(String customerId) {
        return paymentDao.findAllByCustomerId(customerId);
    }

    @Override
    public List<Payment> findAllByCustomerIdAndDate(String customerId, Date date) {
        return paymentDao.findAllByCustomerIdAndDate(customerId, date);
    }

    @Override
    public double getPaymentsTotalByCustomerId(String customerId) {
        List<Payment> debts = paymentDao.findAllByCustomerId(customerId);
        double total = debts.stream().mapToDouble(p -> p.getAmount()).sum();
        return total;
    }

	@Override
	public List<Payment> findAllByDate(Date date) {
		return paymentDao.findAllByDate(date);
	}
}
