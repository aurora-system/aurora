package com.spring.aurora.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.aurora.dao.ProductDao;
import com.spring.aurora.model.Product;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
        //return Collections.emptyList();
    }

    @Override
    public Product findByProductId(String productId) {
        return productDao.findByProductId(productId);
    }
}
