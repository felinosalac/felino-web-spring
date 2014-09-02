package com.felino.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.felino.dao.UserDAO;
import com.felino.model.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	@Override
	public User getUser(final String username) {
		
		getHibernateTemplate().executeFind(new HibernateCallback<User>() {

			@Override
			public User doInHibernate(Session session) throws HibernateException, SQLException {
				
				Query query = session.createQuery("from " + getPersistentClass() + " u where u.username = :username");
				query.setParameter("username", username);
				
				User user = (User) query.uniqueResult();
				
				return user;
			}
		});
		
		return null;
	}
	
}
