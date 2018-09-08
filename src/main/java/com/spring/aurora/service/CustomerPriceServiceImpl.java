package com.spring.aurora.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.aurora.dao.CustomerPriceDao;
import com.spring.aurora.model.CustomerPrice;

@Service("customerPriceService")
@Transactional
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

	@Override
	public void deleteCustomerPrice(String customerId, String productId) {
		customerPriceDao.deleteCustomerPrice(customerId, productId);
	}
}
