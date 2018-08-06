package com.spring.aurora.controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.aurora.model.User;
import com.spring.aurora.model.UserAuthority;
import com.spring.aurora.service.UserService;

@Controller
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("formAction", "/user/add");
		return "users";
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user) {
		logger.debug("User="+user.getUsername());
		Set<UserAuthority> set = new HashSet<>();
		for (String role: user.getRoles()) {
			UserAuthority auth = new UserAuthority();
			auth.setUser(user);
			auth.setAuthority(role);
			set.add(auth);
		}			
		user.setUserAuthority(set);
		userService.insert(user);
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/user/edit/{username}", method = RequestMethod.GET)
	public String editUser(@PathVariable("username") String username, Model model) {
		model.addAttribute("user", userService.findByUsername(username));
		model.addAttribute("users", userService.findAll());
		model.addAttribute("formAction", "/user/update");
		return "users";
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user) {
		Set<UserAuthority> set = user.getUserAuthority();
		for (String role: user.getRoles()) {
			UserAuthority auth = new UserAuthority();
			auth.setUser(user);
			auth.setAuthority(role);
			if (!set.contains(auth))
			    set.add(auth);
		}			
		user.setUserAuthority(set);
		userService.update(user);
		return "redirect:/users";
	}
	
	@RequestMapping("/user/delete/{username}")
    public String removePerson(@PathVariable("username") String username){
		userService.delete(username);
		return "redirect:/users";
	}
}
