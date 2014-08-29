package com.felino.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo extends Model {

	@Column(name = "description")
	private String description;

	@Column(name = "title")
	private String title;

	public Todo(Long id, String description, String title) {
		this.setId(id);
		this.description = description;
		this.title = title;
	}
	
	public Todo(){
		super();
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
