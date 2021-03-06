package com.spring.aurora.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users", catalog = "aurora")
public class User implements Serializable {

	private static final long serialVersionUID = 3390708912033322149L;
	
	private String username;
	private String password;
	private boolean enabled;
	private Set<UserAuthority> userAuthority = new HashSet<>(0);
	private List<String> roles = new ArrayList<>(Arrays.asList("ROLE_ADMIN", "ROLE_USER"));

	public User() {
	}

	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String username, String password, boolean enabled, Set<UserAuthority> userAuthority) {
		this(username, password, enabled);
		this.userAuthority = userAuthority;
	}

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<UserAuthority> getUserAuthority() {
		return this.userAuthority;
	}

	public void setUserAuthority(Set<UserAuthority> userAuthority) {
		this.userAuthority = userAuthority;
	}

	@Transient
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
