package com.spring.aurora.dao;

import com.spring.aurora.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String username) {
		List<User> users = sessionFactory.getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username)
				.list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User insert(User user) {
		logger.info("User="+user.getUsername());
		return (User) sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void delete(String username) {
		sessionFactory.getCurrentSession()
			.createQuery("delete from User where username=?")
			.setParameter(0, username)
			.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from User")
				.list();
	}
}