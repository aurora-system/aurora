package com.spring.aurora.service;

import com.spring.aurora.model.CustomerPrice;

import java.util.List;

public interface CustomerPriceService {
    CustomerPrice saveOrUpdate(CustomerPrice customerPrice);
    List<CustomerPrice> findAllByCustomerId(String customerId);
    List<CustomerPrice> findAllByProductId(String productId);
    void deleteCustomerPrice(String customerId, String productId);
}
