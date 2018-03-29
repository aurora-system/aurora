package com.spring.aurora.dao;

import com.spring.aurora.model.CustomerPrice;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class CustomerPriceDaoImpl implements CustomerPriceDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CustomerPrice saveOrUpdate(CustomerPrice customerPrice) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customerPrice);
        return customerPrice;
    }

    @Override
    public List<CustomerPrice> findAllByCustomerId(String customerId) {
        Session session = sessionFactory.getCurrentSession();
        List<CustomerPrice> prices = session.createCriteria(CustomerPrice.class)
                .add(Restrictions.eq("customerId", customerId)).list();
        return prices;
    }

    @Override
    public List<CustomerPrice> findAllByProductId(String productId) {
        return Collections.emptyList();
    }

	@Override
	public void deleteCustomerPrice(String customerId, String productId) {
		
		Session session = this.sessionFactory.getCurrentSession();

		Query deletePriceQuery = session.createQuery("delete from CustomerPrice cp where cp.customerId = :customerId and cp.productId = :productId");
		deletePriceQuery.setParameter("customerId", customerId);
		deletePriceQuery.setParameter("productId", productId);
		deletePriceQuery.executeUpdate();
	}
}
