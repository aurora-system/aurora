package com.spring.aurora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.aurora.dao.UserDao;
import com.spring.aurora.model.User;

//@Service
//@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void insert(User user) {
        this.userDao.insert(user);
    }

    @Override
    public void update(User user) {
        this.userDao.update(user);
    }

    @Override
    public void delete(String username) {
        this.userDao.delete(username);
    }

    @Override
    public User findByUsername(String username) {
        return this.userDao.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return this.userDao.findAll();
    }

}
