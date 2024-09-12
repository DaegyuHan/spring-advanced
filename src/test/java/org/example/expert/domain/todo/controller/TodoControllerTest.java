package org.example.expert.domain.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.service.TodoService;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.data.todo.TodoMockDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private TodoService todoService;



    @Autowired
    private TodoController todoController;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    // saveTodo 테스트 메서드
    @Test
    public void todo_등록_성공() throws Exception {
        // given
        TodoSaveRequest todoSaveRequest = TodoMockDataUtil.todoSaveRequest();

        TodoSaveResponse todoSaveResponse = TodoMockDataUtil.todoSaveResponse();

        given(todoService.saveTodo(any(), any())).willReturn(todoSaveResponse);

        // when-then
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoSaveRequest)))
                .andExpect(status().isOk());
    }


    @Test
    public void todo_목록_조회_성공() throws Exception {
        // given
        Page<TodoResponse> todoResponsePage = TodoMockDataUtil.todoResponsePage();
        given(todoService.getTodos(anyInt(), anyInt())).willReturn(todoResponsePage);

        // when-then
        mockMvc.perform(get("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void todo_조회_성공() throws Exception {
        // given
        long todoId = 1L;

        TodoResponse todoResponse = TodoMockDataUtil.todoResponse();

        given(todoService.getTodo(anyLong())).willReturn(todoResponse);

        // when-then
        mockMvc.perform(get("/todos/{todoId}", todoId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}