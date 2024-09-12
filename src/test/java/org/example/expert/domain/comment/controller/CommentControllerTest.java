package org.example.expert.domain.comment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.expert.data.comment.CommentMockDataUtil;
import org.example.expert.domain.comment.dto.request.CommentSaveRequest;
import org.example.expert.domain.comment.dto.response.CommentSaveResponse;
import org.example.expert.domain.comment.service.CommentService;
import org.example.expert.domain.todo.controller.TodoController;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();


    @MockBean
    private CommentService commentService;


//    @Test
//    public void comment_댓글_정상저장된다() throws Exception {
//        // given
//        long todoId = 1L;
//        CommentSaveRequest commentSaveRequest = CommentMockDataUtil.commentSaveRequest();
//        CommentSaveResponse commentSaveResponse = CommentMockDataUtil.commentSaveResponse();
//
//        given(commentService.saveComment(any(), anyLong(), any())).willReturn(commentSaveResponse);
//
//        // when-then
//        mockMvc.perform(post("/todos/{todoId}/comments", todoId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(commentSaveRequest)))
//                .andExpect(status().isOk());
//    }

    @Test
    void getComments() {
    }
}