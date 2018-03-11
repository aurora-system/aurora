package com.spring.aurora.service;

import com.spring.aurora.dao.CustomerPriceDao;
import com.spring.aurora.model.CustomerPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerPriceService")
public class CustomerPriceServiceImpl implements CustomerPriceService {

    @Autowired
    private CustomerPriceDao customerPriceDao;

    @Override
    public CustomerPrice saveOrUpdate(CustomerPrice customerPrice) {
        return customerPriceDao.saveOrUpdate(customerPrice);
    }

    @Override
    public List<CustomerPrice> findAllByCustomerId(String customerId) {
        return customerPriceDao.findAllByCustomerId(customerId);
    }

    @Override
    public List<CustomerPrice> findAllByProductId(String productId) {
        return customerPriceDao.findAllByProductId(productId);
    }
}
