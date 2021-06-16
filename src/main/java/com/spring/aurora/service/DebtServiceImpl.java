package com.spring.aurora.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.aurora.dao.DebtDao;
import com.spring.aurora.model.Debt;

@Service("debtService")
@Transactional
public class DebtServiceImpl implements DebtService {
    //private static final Logger logger = LoggerFactory.getLogger(DebtServiceImpl.class);

    @Autowired
    private DebtDao debtDao;

    @Override
    public Debt insert(Debt debt) {
        return this.debtDao.save(debt);
    }

    @Override
    public void delete(Debt debt) {
        this.debtDao.delete(debt);
    }

    @Override
    public List<Debt> findAllByCustomerId(long customerId) {
        return this.debtDao.findAllByCustomerId(customerId);
    }

    @Override
    public List<Debt> findAllByCustomerIdAndDate(long customerId, Date date) {
        return new ArrayList<>();
    }

    @Override
    public double findDebtsTotalByCustomerId(long customerId) {
        List<Debt> debts = this.debtDao.findAllByCustomerId(customerId);
        double total = debts.stream().mapToDouble(Debt::getAmount).sum();
        return total;
    }

    @Override
    public double findTotalARs() {
        List<Debt> debts = new ArrayList<>();
        this.debtDao.findAll().forEach(debts::add);
        double total = debts.stream().mapToDouble(Debt::getAmount).sum();
        return total;
    }
}
