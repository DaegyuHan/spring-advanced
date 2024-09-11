package org.example.expert.domain.todo.service;

import org.example.expert.client.WeatherClient;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Mock
    private WeatherClient weatherClient;


    @Test
    void 일정_저장() {
        // given
        long todoId = 1L;
        AuthUser authUser = new AuthUser(1L, "email", UserRole.USER);
        User user = User.fromAuthUser(authUser);
        TodoSaveRequest todoSaveRequest = new TodoSaveRequest("title", "contents");

        String weather = "Sunny";
        given(weatherClient.getTodayWeather()).willReturn(weather);

        Todo todo = new Todo("title", "contents", weather, user);
        given(todoRepository.save(any(Todo.class))).willReturn(todo);
        ReflectionTestUtils.setField(todo, "id", todoId);

        // when
        TodoSaveResponse todoSaveResponse = todoService.saveTodo(authUser, todoSaveRequest);
        // then
        assertNotNull(todoSaveResponse);
        assertEquals(1L, todoSaveResponse.getId());
        assertEquals("title", todoSaveResponse.getTitle());
        assertEquals("contents", todoSaveResponse.getContents());
        assertEquals(weather, todoSaveResponse.getWeather());
        assertEquals(user.getId(), todoSaveResponse.getUser().getId());
        assertEquals(user.getEmail(), todoSaveResponse.getUser().getEmail());
    }

    @Test
    void 일정목록_조회() {
        // given
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page - 1, size);
        AuthUser authUser = new AuthUser(1L, "email", UserRole.USER);
        User user = User.fromAuthUser(authUser);

        Todo todo1 = new Todo("title", "contents", "weather", user);
        Todo todo2 = new Todo("title", "contents", "weather", user);

        Page<Todo> todoPage = new PageImpl<>(List.of(todo1, todo2), pageable, 2);

        given(todoRepository.findAllByOrderByModifiedAtDesc(pageable)).willReturn(todoPage);
        // when

        Page<TodoResponse> result = todoService.getTodos(page, size);
        // then
        assertNotNull(result);
    }

    @Test
    void 일정_조회() {
        // given
        long todoId = 1L;
        AuthUser authUser = new AuthUser(1L, "email", UserRole.USER);
        User user = User.fromAuthUser(authUser);
        Todo todo = new Todo("title", "contents", "weather", user);
        ReflectionTestUtils.setField(todo, "id", todoId);

        given(todoRepository.findByIdWithUser(todoId)).willReturn(Optional.of(todo));

        // when
        TodoResponse todoResponse = todoService.getTodo(todoId);
        // then
        assertNotNull(todoResponse);
        assertEquals(1, todoResponse.getId());
        assertEquals("title", todoResponse.getTitle());
        assertEquals("contents", todoResponse.getContents());
        assertEquals("weather", todoResponse.getWeather());
        assertEquals(user.getId(), todoResponse.getUser().getId());

    }
}