package org.example.expert.domain.comment.service;

import org.example.expert.domain.comment.entity.Comment;
import org.example.expert.domain.comment.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
@Import(AnnotationAwareAspectJAutoProxyCreator.class)
class CommentAdminControllerTest {

    @InjectMocks
    private CommentAdminService commentAdminService;
    @Mock
    private CommentRepository commentRepository;

//    @BeforeEach
//    void setUp() {
//        // Character SET에 대한 깨짐 방지
//        this.mvc = MockMvcBuilders.webAppContextSetup(context)
//                .addFilter(new CharacterEncodingFilter("UTF-8", true))
//                .alwaysDo(print())
//                .build();
//    }

    @Test
    void Admin_댓글_삭제() {
        // given
        long commentId = 1L;
        Comment comment = new Comment();
        ReflectionTestUtils.setField(comment, "id", commentId);

        doNothing().when(commentRepository).deleteById(commentId);

        // when
        commentAdminService.deleteComment(commentId);

        // then
        // verify(commentRepository, times(1)).deleteById(anyLong());
        assertTrue(commentRepository.findById(commentId).isEmpty());
    }


}