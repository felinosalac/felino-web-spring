package com.felino.dao;

import com.felino.model.User;

public interface UserDAO extends GenericDAO<User> {
	
	public User getUser(String username);

}
