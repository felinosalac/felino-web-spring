package com.felino.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

import com.felino.dao.UserDAO;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		com.felino.model.User domainUser = userDAO.getUser(username);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		User user = new User(domainUser.getUsername(), 
			                domainUser.getPassword(), 
			                enabled, 
			                accountNonExpired, 
			                credentialsNonExpired, 
			                accountNonLocked,
			                getAuthorities(domainUser.getRole().getId()));
		
		return user;
		
	}

	public Collection<? extends GrantedAuthority> getAuthorities(Long role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }
     
    public List<String> getRoles(Long role) {
 
        List<String> roles = new ArrayList<String>();
 
        if (role.intValue() == 1) {
            roles.add("ROLE_MODERATOR");
            roles.add("ROLE_ADMIN");
            roles.add("ROLE_USER");
        } else if (role.intValue() == 2) {
            roles.add("ROLE_MODERATOR");
        }
        return roles;
    }
     
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
