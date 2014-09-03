package com.felino.web.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.felino.entity.enums.AuthorityType;

public abstract class BaseController {

	protected final Logger LOG = Logger.getLogger(getClass());
	
	protected boolean hashAnyRole(AuthorityType... authorityTypes) {
		
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof User) {
			Collection<? extends GrantedAuthority> authorities = ((User) principal).getAuthorities();
			
			for (AuthorityType authorityType : authorityTypes) {
				if (authorities.contains(new SimpleGrantedAuthority(authorityType.toString()))) {
					return true;
				}
			}
			
		}
		
		return false;
		
	}
	
	protected User currentUser() {
		
		User user = null;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			user = (User) authentication.getPrincipal();
		}
		
		return user;
		
	}
	
}
