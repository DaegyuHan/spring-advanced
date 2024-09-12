package org.example.expert.data.comment;

import org.example.expert.data.user.UserMockDataUtil;
import org.example.expert.domain.comment.dto.request.CommentSaveRequest;
import org.example.expert.domain.comment.dto.response.CommentSaveResponse;

public class CommentMockDataUtil {
    public static CommentSaveRequest commentSaveRequest() {
        return new CommentSaveRequest("contents");
    }

    public static CommentSaveResponse commentSaveResponse() {
        return new CommentSaveResponse(1L, "contents", UserMockDataUtil.userResponse());
    }
}
