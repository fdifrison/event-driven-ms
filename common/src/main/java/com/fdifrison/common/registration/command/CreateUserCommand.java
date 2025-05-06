package com.fdifrison.common.registration.command;

import com.fdifrison.common.registration.dto.UserDTO;
import com.fdifrison.common.registration.dto.UserStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class CreateUserCommand {

    @TargetAggregateIdentifier
    private UUID id;
    private String username;
    private String email;
    private UserStatus status;


    public CreateUserCommand(UserDTO user) {
        this.id = user.id();
        this.username = user.username();
        this.email = user.email();
        this.status = user.status();

    }

    public UUID getId() {
        return id;
    }

    public CreateUserCommand setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CreateUserCommand setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateUserCommand setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserStatus getStatus() {
        return status;
    }

    public CreateUserCommand setStatus(UserStatus status) {
        this.status = status;
        return this;
    }
}
