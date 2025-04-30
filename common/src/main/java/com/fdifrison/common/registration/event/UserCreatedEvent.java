package com.fdifrison.common.registration.event;

public record UserCreatedEvent(String username,
                               String email) {
}
