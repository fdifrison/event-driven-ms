package com.fdifrison.user;

import com.fdifrison.common.registration.event.UserCreatedEvent;
import com.fdifrison.common.registration.event.UserDeletedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
class UserProjection {

    private final UserService userService;

    public UserProjection(UserService userService) {
        this.userService = userService;
    }

    @EventHandler
    public void on(UserCreatedEvent event) {
        userService.createUser(event);
    }

    @EventHandler
    public void on(UserDeletedEvent event) {
        userService.deleteUser(event.id());
    }

}
