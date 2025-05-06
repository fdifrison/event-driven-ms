package com.fdifrison.common.registration.event;

import com.fdifrison.common.registration.command.CreateUserCommand;
import com.fdifrison.common.registration.dto.UserDTO;
import com.fdifrison.common.registration.dto.UserStatus;

import java.util.UUID;

public class UserCreatedEvent {

    private UUID id;
    private String username;
    private String email;
    private UserStatus status;


    public UserCreatedEvent(UserDTO user) {
        this.id = user.id();
        this.username = user.username();
        this.email = user.email();
        this.status = user.status();

    }

    public UUID getId() {
        return id;
    }

    public UserCreatedEvent setId(UUID id) {
        this.id = id;
        return this;
    }

    public UserStatus getStatus() {
        return status;
    }

    public UserCreatedEvent setStatus(UserStatus status) {
        this.status = status;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserCreatedEvent setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserCreatedEvent setEmail(String email) {
        this.email = email;
        return this;
    }
}
