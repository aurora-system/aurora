package com.spring.aurora.dao;

import java.util.List;

import com.spring.aurora.model.User;

public interface UserDao {

	User insert(User user);
	void update(User user);
	void delete(String username);
	User findByUsername(String username);
	List<User> findAll();
}