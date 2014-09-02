package com.felino.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model, @RequestParam(value="error", required=false) String error) {
 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
		if(name != null && error != null) {
			model.addAttribute("username", name);
		}
		
		if(error != null && error.equalsIgnoreCase("1")) {
			model.addAttribute("error_message", "The username and password you entered did not match our records. Please double-check and try again.");
		}
		
		model.addAttribute("message", "Spring 3 MVC Hello World");
		
		return "home";
 
	}
	
}
