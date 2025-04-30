package com.fdifrison.common.registration.command;

import com.fdifrison.common.registration.dto.UserDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateUserCommand {

    @TargetAggregateIdentifier
    private String username;
    private String email;

    public CreateUserCommand(String username,
                             String email) {
        this.username = username;
        this.email = email;
    }

    public CreateUserCommand(UserDTO user) {
        this(user.username(), user.email());
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
}
