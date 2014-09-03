package com.felino.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.felino.entity.enums.AuthorityType;

@Controller
public class LoginController extends BaseController{
	
	@RequestMapping(value = "/")
	public String index() {
		
		LOG.debug("redirect to home page");
		return "redirect:/landing-page";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
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
		
		return "public/home";
 
	}
	
	@RequestMapping(value = "/landing-page")
	public String landingPage() {
		
		if (hashAnyRole(AuthorityType.ROLE_ADMIN)) {
			return "redirect:/admin";
		}
		
		if (hashAnyRole(AuthorityType.ROLE_MODERATOR)) {
			return "redirect:/moderator";
		}
		
		if (hashAnyRole(AuthorityType.ROLE_USER)) {
			return "redirect:/user";
		}

		return "redirect:/logout";
	}
	
}
