package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserStatus;
import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
abstract class BaseUser<T extends BaseUser<T>> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public UUID getId() {
        return id;
    }

    public BaseUser<T> setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public T setUsername(String username) {
        this.username = username;
        return (T) this;
    }

    public String getEmail() {
        return email;
    }

    public T setEmail(String email) {
        this.email = email;
        return (T) this;
    }

    public UserStatus getStatus() {
        return status;
    }

    public T setStatus(UserStatus status) {
        this.status = status;
        return (T) this;
    }
}
