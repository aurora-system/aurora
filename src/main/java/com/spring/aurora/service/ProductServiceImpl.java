package com.spring.aurora.service;

import com.spring.aurora.dao.ProductDao;
import com.spring.aurora.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product saveOrUpdate(Product product) {
        return productDao.saveOrUpdate(product);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
        //return Collections.emptyList();
    }
}
