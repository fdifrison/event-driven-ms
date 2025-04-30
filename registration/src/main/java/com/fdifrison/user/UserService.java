package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserDTO;
import com.fdifrison.common.registration.dto.UserStatus;
import com.fdifrison.common.registration.event.UserCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;
import java.util.UUID;

@Service
@RestControllerAdvice
class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserDTO createUser(UserDTO userDTO) {
        var user = userRepository.findByEmailAndStatusNot(userDTO.email(), UserStatus.CANCELLED);
        checkIfActiveUserWithSameEmailExist(userDTO.email(), user);
        var saved = userRepository.save(userMapper.toUser(userDTO).setStatus(UserStatus.PENDING));
        return userMapper.toUserDTO(saved);
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createUser(UserCreatedEvent event) {
        var user = userRepository.findByEmailAndStatusNot(event.email(), UserStatus.CANCELLED);
        checkIfActiveUserWithSameEmailExist(event.email(), user);
        userRepository.save(userMapper.toUser(event).setStatus(UserStatus.PENDING));
    }

    private static void checkIfActiveUserWithSameEmailExist(String email, Optional<User> user) {
        if (user.isPresent()) {
            throw new RuntimeException("User already exists with email: " + email);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserDTO deleteUser(UUID id) {
        var user = userRepository.findByIdAndStatusNot(id, UserStatus.CANCELLED).orElseThrow();
        user.setStatus(UserStatus.CANCELLED);
        return userMapper.toUserDTO(user);
    }

    public UserDTO getUser(UUID id) {
        var user = userRepository.findByIdAndStatusNot(id, UserStatus.CANCELLED).orElseThrow();
        return userMapper.toUserDTO(user);
    }
}
