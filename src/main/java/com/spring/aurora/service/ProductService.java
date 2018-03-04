package com.spring.aurora.service;

import com.spring.aurora.model.Product;

import java.util.List;

public interface ProductService {
    Product saveOrUpdate(Product product);
    List<Product> findAll();
}
