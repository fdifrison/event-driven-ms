package com.fdifrison.user;

import com.fdifrison.common.registration.command.CreateUserCommand;
import com.fdifrison.common.registration.command.DeleteUserCommand;
import com.fdifrison.common.registration.dto.UserDTO;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
@Validated
class UserCommandController {

    private final CommandGateway commandGateway;

    public UserCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return commandGateway.sendAndWait(new CreateUserCommand(userDTO));
    }

    @DeleteMapping("{id}")
    public UserDTO deleteUser(@PathVariable UUID id) {
        return commandGateway.sendAndWait(new DeleteUserCommand(id));
    }
}

