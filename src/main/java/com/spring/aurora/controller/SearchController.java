package com.spring.aurora.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.aurora.service.CustomerService;

@Controller
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
    private CustomerService customerService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.debug("index()");
        return "redirect:/customers/list";
    }
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Locale locale, Model model) {
		logger.debug("search()");
		return "search";
	}
}
