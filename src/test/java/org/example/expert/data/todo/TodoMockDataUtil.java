package org.example.expert.data.todo;

import org.example.expert.data.user.UserMockDataUtil;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoMockDataUtil {
    public static TodoSaveRequest todoSaveRequest() {
        return new TodoSaveRequest("title", "contents");
    }


    public static TodoSaveResponse todoSaveResponse() {
        return new TodoSaveResponse(1L, "title", "contents", "weather", UserMockDataUtil.userResponse());
    }

    public static TodoResponse todoResponse() {
        return new TodoResponse(1L, "title", "contents", "weather", UserMockDataUtil.userResponse(), LocalDateTime.now(), LocalDateTime.now());
    }

    public static Page<TodoResponse> todoResponsePage() {
        List<TodoResponse> todoResponseList = new ArrayList<>();
        for (int i = 0; i<10; i++) {
            todoResponseList.add(new TodoResponse((long)i, "title", "contents", "weather", UserMockDataUtil.userResponse(), LocalDateTime.now(), LocalDateTime.now()));
        }

        Pageable pageable = PageRequest.of(1, 10);
        return new PageImpl<>(todoResponseList, pageable, todoResponseList.size());
    }
}
