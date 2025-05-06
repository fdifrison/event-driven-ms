package com.fdifrison.user;

import com.fdifrison.common.registration.event.UserCreatedEvent;
import com.fdifrison.common.registration.event.UserDeletedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("user-group")
class UserProjection {

    private final UserService userService;

    public UserProjection(UserService userService) {
        this.userService = userService;
    }


    @EventHandler
    public void on(UserCreatedEvent event) {
        userService.projectCreateUser(event);
    }

    @EventHandler
    public void on(UserDeletedEvent event) {
        userService.projectDeleteUser(event.id());
    }

}
