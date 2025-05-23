package com.fdifrison.common.registration.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserDTO(UUID id,
                      @NotEmpty String username,
                      @NotEmpty String email) {

}
