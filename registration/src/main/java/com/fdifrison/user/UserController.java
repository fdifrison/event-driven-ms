package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
@Validated
class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("{id}")
    public UserDTO deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }

    @GetMapping("{id}")
    public UserDTO getUser(@PathVariable UUID id) {
        return userService.getUser(id);
    }
}

