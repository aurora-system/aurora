package com.spring.aurora.dao;

import com.spring.aurora.model.CustomerPrice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class CustomerPriceDaoImpl implements CustomerPriceDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CustomerPrice saveOrUpdate(CustomerPrice customerPrice) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customerPrice);
        return customerPrice;
    }

    @Override
    public List<CustomerPrice> findAllByCustomerId(String customerId) {
        return Collections.emptyList();
    }

    @Override
    public List<CustomerPrice> findAllByProductId(String productId) {
        return Collections.emptyList();
    }
}
