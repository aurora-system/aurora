package com.spring.aurora.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Transactional
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help(Model model) {
		
		
		return "help-page";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error,
			@RequestParam boolean logout,
			Model model) {
		logger.debug("Received request to show login page");

		// Add an error message to the model if login is unsuccessful
		// The 'error' parameter is set to true based on the when the authentication has failed. 
		// We declared this under the authentication-failure-url attribute inside the spring-security.xml
		/* See below:
		 <form-login 
				login-page="/krams/auth/login" 
				authentication-failure-url="/krams/auth/login?error=true" 
				default-target-url="/krams/main/common"/>
		 */
		if (error == true) {
			// Assign an error message
			model.addAttribute("error", "You have entered an invalid username or password");
		}
		if (logout == true) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		
		// This will resolve to /WEB-INF/jsp/login.jsp
		return "login";
	}
     
	@RequestMapping(value = "/403", method = RequestMethod.GET)
 	public String getDeniedPage(Model model) {
		logger.debug("Received request to show denied page");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("username", userDetail.getUsername());
		}
		// This will resolve to /WEB-INF/jsp/403.jsp
		return "403";
	}
	
//    @RequestMapping(value = "/home", method = RequestMethod.POST)
//    public String login(@RequestParam String username, @RequestParam String password, Model model) {
//    	User u = this.userService.loginUser(username, password);
//    	
//        if (u == null) {
//        	return "login";
//        } else {
//        	model.addAttribute("username", username);
//            model.addAttribute("user", u);
//            return "user";
//        }
//    }
    
}
