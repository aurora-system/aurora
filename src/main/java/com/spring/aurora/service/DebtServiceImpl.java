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
        return debtDao.insert(debt);
    }
    
    @Override
    public Debt delete(Debt debt) {
        return debtDao.delete(debt);
    }

    @Override
    public List<Debt> findAllByCustomerId(String customerId) {
        return debtDao.findAllByCustomerId(customerId);
    }

    @Override
    public List<Debt> findAllByCustomerIdAndDate(String customerId, Date date) {
        return new ArrayList<>();
    }

    @Override
    public double findDebtsTotalByCustomerId(String customerId) {
        List<Debt> debts = debtDao.findAllByCustomerId(customerId);
        double total = debts.stream().mapToDouble(p -> p.getAmount()).sum();
        return total;
    }

    @Override
    public double findTotalARs() {
        List<Debt> debts = debtDao.findAll();
        double total = debts.stream().mapToDouble(Debt::getAmount).sum();
        return total;
    }
}
