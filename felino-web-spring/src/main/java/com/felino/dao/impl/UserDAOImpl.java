package com.felino.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.felino.dao.UserDAO;
import com.felino.model.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(final String username) {
		
		List<User> users = getHibernateTemplate().executeFind(new HibernateCallback<List<User>>() {

			@Override
			public List<User> doInHibernate(Session session) throws HibernateException, SQLException {
				
				Query query = session.createQuery("from " + getPersistentClass().getName() + " u where u.username = :username");
				query.setParameter("username", username);
				
				return query.list();
			}
		});
		
		if(users != null && !users.isEmpty()) {
			return users.get(0);
		}
		
		return null;
	}
	
}
