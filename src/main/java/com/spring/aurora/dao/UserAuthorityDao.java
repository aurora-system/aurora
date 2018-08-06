package com.spring.aurora.dao;

import java.util.List;

import com.spring.aurora.model.UserAuthority;

public interface UserAuthorityDao {

	UserAuthority insert(UserAuthority userAuth);
	void update(UserAuthority userAuth);
	void delete(int id);
	List<UserAuthority> findAllByUsername(String username);
}
