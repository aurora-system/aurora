package com.spring.aurora.dao;

import com.spring.aurora.model.CustomerPrice;

import java.util.List;

public interface CustomerPriceDao {
    CustomerPrice saveOrUpdate(CustomerPrice customerPrice);
    List<CustomerPrice> findAllByCustomerId(String customerId);
    List<CustomerPrice> findAllByProductId(String productId);
}
