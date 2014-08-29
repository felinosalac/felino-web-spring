package com.felino.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.felino.dao.GenericDAO;
import com.felino.model.Model;

public class GenericDAOImpl<T extends Model> extends AbstractDaoSupport implements GenericDAO<T>{

	private Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	@Autowired
	public GenericDAOImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];  
		
	}
	
	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public T find(long id) {
		return (T)getHibernateTemplate().get(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> all() {
		 return getHibernateTemplate().find("from "  + this.persistentClass.getName());
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByPage(final int page, final int count) {
		
		
		List<T> list = getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query q = session.createQuery("from " + persistentClass.getName() + " entity order by entity.dateCreated desc");
				
				if(page != 0 || count != 0) {
					q.setMaxResults(count);
					q.setFirstResult(page - 1);
				}
				
				return q.list();
			}
		});
		
		return list;
	}

}
