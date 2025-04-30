package com.fdifrison.common.registration.event;

import com.fdifrison.common.registration.command.CreateUserCommand;

public record UserCreatedEvent(String username,
                               String email) {

    public UserCreatedEvent(CreateUserCommand command) {
        this(command.getUsername(), command.getEmail());
    }
}
