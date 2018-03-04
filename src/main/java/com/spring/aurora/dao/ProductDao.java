package com.spring.aurora.dao;

import com.spring.aurora.model.Product;

import java.util.List;

public interface ProductDao {
    Product saveOrUpdate(Product product);
    List<Product> findAll();
}
