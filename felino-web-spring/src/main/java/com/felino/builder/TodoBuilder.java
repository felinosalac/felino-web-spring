package com.felino.builder;

import com.felino.domain.Todo;

public class TodoBuilder {
	
	private Long id;
	
	private String description;
	
	private String title;

	
	public TodoBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public TodoBuilder description(String description){
		this.description =  description;
		return this;
	}
	
	public TodoBuilder title(String title){
		this.title =  title;
		return this;
	}
	
	public Todo build(){
		return new Todo(id, description, title);
	}
}
