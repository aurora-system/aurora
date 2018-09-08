package com.spring.aurora.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.aurora.dao.UserDao;
import com.spring.aurora.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(String username) {
		userDao.delete(username);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
