package com.spring.aurora.service;

import com.spring.aurora.dao.DebtDao;
import com.spring.aurora.model.Debt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service("debtService")
public class DebtServiceImpl implements DebtService {
    private static final Logger logger = LoggerFactory.getLogger(DebtServiceImpl.class);

    @Autowired
    private DebtDao debtDao;

    @Override
    public Debt insert(Debt debt) {
        return debtDao.insert(debt);
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

}
