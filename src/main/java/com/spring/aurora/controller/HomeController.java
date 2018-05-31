package com.spring.aurora.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

import javax.naming.InitialContext;
import javax.sql.DataSource;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	DataSource dataSource;

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
	
	@RequestMapping(value = "/loginx", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error,
			@RequestParam boolean logout,
			Model model) {
		logger.debug("Received request to show login page");

		/* Add an error message to the model if login is unsuccessful
		   The 'error' parameter is set to true based on the when the authentication has failed.
		   We declared this under the authentication-failure-url attribute inside the spring-security.xml
		   See below:
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
	
    /*@RequestMapping(value = "/home", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
    	User u = this.userService.loginUser(username, password);

        if (u == null) {
        	return "login";
        } else {
        	model.addAttribute("username", username);
            model.addAttribute("user", u);
            return "user";
        }
    }*/

    @RequestMapping ("/alter")
	public String alter(Model model) throws SQLException {
		String alterStmt = "ALTER TABLE `aurora`.`orders` ADD COLUMN `slim_buy_count` INT(11) NOT NULL DEFAULT '0' AFTER `cont_round_count`, ADD COLUMN `round_buy_count` INT(11) NOT NULL DEFAULT '0' AFTER `slim_buy_count`";

        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
		boolean res = stmt.execute(alterStmt);
		model.addAttribute("result", res);
    	return "alter";
	}
}
