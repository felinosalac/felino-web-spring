package com.felino.dao;

import java.util.List;

import com.felino.model.Model;

public interface GenericDAO<T extends  Model> {
	
	void save(T entity);
	
	T find(long id);
	
	List<T> all();
	
	void update(T entity);
	
	void delete(T entity);

	List<T> findByPage(int page, int count);
}
