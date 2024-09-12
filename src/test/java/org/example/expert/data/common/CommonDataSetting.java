package org.example.expert.data.common;

import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.test.util.ReflectionTestUtils;

public class CommonDataSetting {
    public final static User TEST_USER1 = new User("id", "email", UserRole.USER);


    // 등록된 일정 생성
    public final static Todo getTodoSuccessRegister() {
        long todoId = 1L;
        Todo todo = new Todo("title", "contents", "weather", getUserSuccessRegister());
        ReflectionTestUtils.setField(todo, "id", todoId);

        return todo;
    }

    // 등록된 유저 생성
    public final static User getUserSuccessRegister() {
        AuthUser authUser = new AuthUser(1L, "a@a.com", UserRole.USER);
        User user = User.fromAuthUser(authUser);  // 일정을 만든 유저
        return user;
    }

    // 등록된 담당자 생성
    public final static Manager getManagerSuccessRegister() {
        long userId = 2L;
        User managerUser =  new User("email", "pwd", UserRole.USER);
        ReflectionTestUtils.setField(managerUser, "id", userId);

        long managerId = 1L;
        Manager savedManagerUser = new Manager(managerUser, getTodoSuccessRegister());
        ReflectionTestUtils.setField(savedManagerUser, "id", managerId);
        return savedManagerUser;
    }
}
