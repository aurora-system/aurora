package com.spring.aurora.service;

import java.sql.Date;
import java.util.ArrayList;
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
        return this.paymentDao.save(payment);
    }

    @Override
    public List<Payment> findAllByCustomerId(long customerId) {
        return this.paymentDao.findAllByCustomerId(customerId);
    }

    @Override
    public List<Payment> findAllByCustomerIdAndDate(long customerId, Date date) {
        return this.paymentDao.findAllByCustomerIdAndCreatedAt(customerId, date);
    }

    @Override
    public double getPaymentsTotalByCustomerId(long customerId) {
        List<Payment> payments = this.paymentDao.findAllByCustomerId(customerId);
        double total = payments.stream().mapToDouble(p -> p.getAmount() + p.getWithholdingTax()).sum();
        return total;
    }

    @Override
    public List<Payment> findAllByDate(Date date) {
        return this.paymentDao.findAllByCreatedAt(date);
    }

    @Override
    public double getTotalPayments() {
        List<Payment> payments = new ArrayList<>();
        this.paymentDao.findAll().forEach(payments::add);
        double total = payments.stream().mapToDouble(p -> p.getAmount() + p.getWithholdingTax()).sum();
        return total;
    }
}
