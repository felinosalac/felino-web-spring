package com.felino.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {

	@RequestMapping(value="/404", method = RequestMethod.GET)
	public String pageNotFound() {
		return "redirect:/login";
	}
	
	@RequestMapping(value="/403", method = RequestMethod.GET)
	public String pageForbidden() {
		return "redirect:/login";
	}
	
}
