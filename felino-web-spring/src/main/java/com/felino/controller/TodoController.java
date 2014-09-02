package com.felino.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.felino.exception.TodoNotFoundException;
import com.felino.model.Todo;
import com.felino.model.dto.TodoDTO;
import com.felino.service.ToDoService;

@Controller
public class TodoController {
	
	@Autowired
	private ToDoService toDoService;

    @RequestMapping(value = "/todo", method = RequestMethod.GET) //consumes={MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE
    @ResponseBody
    public List<TodoDTO> findAll() {
        List<Todo> models = toDoService.findAll();
        return createDTOs(models);
    }
    
    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TodoDTO findById(@PathVariable("id") Long id) throws TodoNotFoundException {
        Todo found = toDoService.findById(id);
        return createDTO(found);
    }

    private List<TodoDTO> createDTOs(List<Todo> models) {
        List<TodoDTO> dtos = new ArrayList<TodoDTO>();

        for (Todo model: models) {
            dtos.add(createDTO(model));
        }

        return dtos;
    }

    private TodoDTO createDTO(Todo model) {
    	
    	TodoDTO dto = new TodoDTO();

        dto.setId(model.getId());
        dto.setDescription(model.getDescription());
        dto.setTitle(model.getTitle());

        return dto;
    }
	
}
