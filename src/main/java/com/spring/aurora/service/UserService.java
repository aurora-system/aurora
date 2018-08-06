package com.spring.aurora.service;

import java.util.List;

import com.spring.aurora.model.User;

public interface UserService {

	void insert(User user);
	void update(User user);
	void delete(String username);
	User findByUsername(String username);
	List<User> findAll();
}
