package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserDTO;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Validated
class UserCommandController {

    private final CommandGateway commandGateway;
    private final UserMapper userMapper;

    public UserCommandController(CommandGateway commandGateway, UserMapper userMapper) {
        this.commandGateway = commandGateway;
        this.userMapper = userMapper;
    }

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return commandGateway.sendAndWait(userMapper.toCreateUserCommand(userDTO));
    }
}

