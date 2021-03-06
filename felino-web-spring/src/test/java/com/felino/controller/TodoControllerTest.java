package com.felino.controller;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.felino.builder.TodoBuilder;
import com.felino.configuration.TestContext;
import com.felino.configuration.WebAppContext;
import com.felino.exception.TodoNotFoundException;
import com.felino.model.Todo;
import com.felino.service.ToDoService;
import com.felino.util.TestUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class TodoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ToDoService toDoServiceMock;

    @Autowired 
    private WebApplicationContext ctx;
    
    UUID key = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");
    
    @Before 
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
    
    @Test
    public void findById_TodoEntryFound() throws Exception {
		Todo teachTagalog = new TodoBuilder().id(143L).description("Teach Tagalog Lesson").title("Teaching").build();
		
    	when(toDoServiceMock.findById(143L)).thenReturn(teachTagalog);
    	
    	ResultActions jsonResult = mockMvc.perform(get("/api/todo/{id}", 143L));
    	jsonResult.andExpect(status().isOk())
    	.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))                
        .andExpect(jsonPath("$.id", is(143)))
        .andExpect(jsonPath("$.description", is("Teach Tagalog Lesson")));
    	
    	 verify(toDoServiceMock, times(1)).findById(143L);
    	 verifyNoMoreInteractions(toDoServiceMock);
    }
    
    @Test
    public void findById_TodoEntryNotFound_ShouldReturnHttpStatusCode404() throws Exception {
        when(toDoServiceMock.findById(1L)).thenThrow(new TodoNotFoundException(""));

        mockMvc.perform(get("/api/todo/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(toDoServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(toDoServiceMock);
    }

    @Test
    public void findAll_TodosFound_ShouldReturnFoundTodoEntries() throws Exception {
        Todo first = new TodoBuilder()
                .id(1L)
                .description("Lorem ipsum")
                .title("Foo")
                .build();
        Todo second = new TodoBuilder()
                .id(2L)
                .description("Lorem ipsum")
                .title("Bar")
                .build();

        when(toDoServiceMock.findAll()).thenReturn(Arrays.asList(first, second));
        
        mockMvc.perform(get("/api/todo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[0].title", is("Foo")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[1].title", is("Bar")));

        verify(toDoServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(toDoServiceMock);
    }
    

}