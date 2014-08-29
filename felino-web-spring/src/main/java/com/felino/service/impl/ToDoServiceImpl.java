package com.felino.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felino.dao.TodoDAO;
import com.felino.model.Todo;
import com.felino.service.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService{
	
	@Autowired
	private TodoDAO todoDAO;

	@Override
	public List<Todo> findAll() {
		return todoDAO.all();
	}

	@Override
	public Todo findById(long id) {
		return todoDAO.find(id);
	}

}
