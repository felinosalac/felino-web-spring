package com.felino.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/page")
public class PageController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model, @RequestParam(value="page", required=false) String page) {
 
		return page;
 
	}

}
