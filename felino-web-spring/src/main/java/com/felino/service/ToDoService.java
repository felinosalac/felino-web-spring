package com.felino.service;

import java.util.List;

import com.felino.model.Todo;

/**
 * @author Felino Salac II
 * @date August 6, 2014
 */

public interface ToDoService {

	List<Todo> findAll();

	Todo findById(long l);

}
