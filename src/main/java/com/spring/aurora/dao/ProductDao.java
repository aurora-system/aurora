package com.spring.aurora.dao;

import com.spring.aurora.model.Product;

import java.util.List;

public interface ProductDao {
    Product save(Product product);
    List<Product> findAll();
    Product findByProductId(String productId);
}
