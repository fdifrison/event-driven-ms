package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByIdAndStatusNot(UUID id, UserStatus status);

}
