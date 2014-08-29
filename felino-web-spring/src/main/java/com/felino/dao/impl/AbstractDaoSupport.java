package com.felino.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AbstractDaoSupport extends HibernateDaoSupport {
	
	@Autowired
	public void setGenericDAOHibernate(final SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}
