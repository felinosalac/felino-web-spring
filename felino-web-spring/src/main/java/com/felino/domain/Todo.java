package com.felino.domain;

public class Todo {

	private Long id;

	private String description;

	private String title;

	public Todo(Long id, String description, String title) {
		this.id = id;
		this.description = description;
		this.title = title;
	}
	
	public Todo(){
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
