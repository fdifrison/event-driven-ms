package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserDTO;
import com.fdifrison.common.registration.query.GetUserQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
class UserQueryHandler {

    private final UserService userService;

    UserQueryHandler(UserService userService) {
        this.userService = userService;
    }

    @QueryHandler
    public UserDTO getUserById(GetUserQuery query) {
        return userService.getUser(query.id());
    }
}
