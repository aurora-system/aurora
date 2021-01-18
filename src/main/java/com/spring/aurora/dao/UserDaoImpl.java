package com.spring.aurora.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.aurora.model.User;

//@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    // @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public User findByUsername(String username) {
        List<User> users = this.sessionFactory.getCurrentSession()
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
    public void insert(User user) {
        logger.info("User="+user.getUsername());
        this.sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {
        this.sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Object instance = session.get(User.class, username);
        if (instance != null) {
            session.delete(instance);
            session.flush();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAll() {
        return this.sessionFactory.getCurrentSession()
                .createQuery("from User")
                .list();
    }
}