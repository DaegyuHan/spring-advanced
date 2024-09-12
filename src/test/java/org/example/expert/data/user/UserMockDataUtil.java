package org.example.expert.data.user;

import org.example.expert.domain.user.dto.response.UserResponse;

public class UserMockDataUtil {

    public final static UserResponse userResponse() {
        return new UserResponse(1L, "email");
    }
}
