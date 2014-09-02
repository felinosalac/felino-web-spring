package com.felino.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felino.dao.TodoDAO;
import com.felino.model.Todo;
import com.felino.service.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService{
	
	@Autowired
	private TodoDAO todoDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Todo> findAll() {
		return todoDAO.all();
	}

	@Override
	@Transactional(readOnly = true)
	public Todo findById(long id) {
		return todoDAO.find(id);
	}

}
