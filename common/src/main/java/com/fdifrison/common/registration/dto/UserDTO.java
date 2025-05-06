package com.fdifrison.common.registration.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record UserDTO(UUID id,
                      @NotEmpty String username,
                      @NotEmpty String email,
                      UserStatus status) {

}
