package org.example.expert.data.comment;

import org.example.expert.data.user.UserMockDataUtil;
import org.example.expert.domain.comment.dto.request.CommentSaveRequest;
import org.example.expert.domain.comment.dto.response.CommentResponse;
import org.example.expert.domain.comment.dto.response.CommentSaveResponse;

import java.util.ArrayList;
import java.util.List;

public class CommentMockDataUtil {
    public static CommentSaveRequest commentSaveRequest() {
        return new CommentSaveRequest("contents");
    }

    public static CommentSaveResponse commentSaveResponse() {
        return new CommentSaveResponse(1L, "contents", UserMockDataUtil.userResponse());
    }

    public static CommentResponse commentResponse() {
        return new CommentResponse(1L, "contents", UserMockDataUtil.userResponse());
    }

    public static List<CommentResponse> commentResponseList() {
        List<CommentResponse> commentResponseList = new ArrayList<>();
        for(int i = 0; i<3; i++) {
            commentResponseList.add(new CommentResponse((long) i, "contents", UserMockDataUtil.userResponse()));
        }
        return commentResponseList;
    }
}
