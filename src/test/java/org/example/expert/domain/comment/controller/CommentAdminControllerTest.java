package org.example.expert.domain.comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.example.expert.domain.todo.controller.TodoController;
import org.example.expert.domain.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentAdminController.class)
class CommentAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CommentAdminService commentAdminService;

    @Test
    public void adminComment_를_정상적으로_삭제한다() throws Exception {
        // given
        long commentId = 1L;

        doNothing().when(commentAdminService).deleteComment(anyLong());
        // when-then


        mockMvc.perform(delete("/admin/comments/{commentId}", commentId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}