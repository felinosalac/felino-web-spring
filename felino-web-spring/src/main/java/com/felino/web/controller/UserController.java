package com.felino.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		
		return "user/index";
		
	}

}
