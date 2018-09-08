package com.spring.aurora.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.aurora.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();
		return product;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Product> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Product> products = session.createCriteria(Product.class).list();
        return products;
    }

    @Override
    public Product findByProductId(String productId) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.get(Product.class, productId);
        return product;
    }
}
