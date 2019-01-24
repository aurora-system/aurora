package com.spring.aurora.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.aurora.dao.PaymentDao;
import com.spring.aurora.model.Payment;

@Service("paymentService")
@Transactional
public class PaymentServiceImpl implements PaymentService {
    //private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

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
        List<Payment> payments = paymentDao.findAllByCustomerId(customerId);
        double total = payments.stream().mapToDouble(p -> p.getAmount() + p.getWithholdingTax()).sum();
        return total;
    }

	@Override
	public List<Payment> findAllByDate(Date date) {
		return paymentDao.findAllByDate(date);
	}

    @Override
    public double getTotalPayments() {
        List<Payment> payments = paymentDao.findAll();
        double total = payments.stream().mapToDouble(p -> p.getAmount() + p.getWithholdingTax()).sum();
        return total;
    }
}
