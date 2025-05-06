package com.fdifrison.user;

import com.fdifrison.common.registration.command.CreateUserCommand;
import com.fdifrison.common.registration.dto.UserDTO;
import com.fdifrison.common.registration.dto.UserStatus;
import com.fdifrison.common.registration.event.UserCreatedEvent;
import org.axonframework.eventhandling.gateway.EventGateway;
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
    private final UserViewRepository userViewRepository;
    private final UserMapper userMapper;
    private final EventGateway eventGateway;

    UserService(UserRepository userRepository, UserViewRepository userViewRepository, UserMapper userMapper, EventGateway eventGateway) {
        this.userRepository = userRepository;
        this.userViewRepository = userViewRepository;
        this.userMapper = userMapper;
        this.eventGateway = eventGateway;
    }


    @Transactional
    public void projectCreateUser(UserCreatedEvent event) {
        var user = userViewRepository.findByEmailAndStatusNot(event.getEmail(), UserStatus.CANCELLED);
        checkIfActiveUserWithSameEmailExist(event.getEmail(), user);
        var userView = userMapper.toUserView(event);
        userViewRepository.save(userView.setStatus(UserStatus.PENDING));
    }

    @Transactional
    public UserDTO createUser(CreateUserCommand command) {
        var user = userRepository.findByEmailAndStatusNot(command.getEmail(), UserStatus.CANCELLED);
        checkIfActiveUserWithSameEmailExist(command.getEmail(), user);
        var saved = userRepository.save(userMapper.toUser(command));
        eventGateway.publish(new UserCreatedEvent(userMapper.toUserDTO(saved)));
        return userMapper.toUserDTO(saved);
    }

    private static void checkIfActiveUserWithSameEmailExist(String email, Optional<BaseUser<?>> user) {
        if (user.isPresent()) {
            throw new RuntimeException("User already exists with email: " + email);
        }
    }

    @Transactional
    public void projectDeleteUser(UUID id) {
        var user = userRepository.findByIdAndStatusNot(id, UserStatus.CANCELLED).orElseThrow();
        user.setStatus(UserStatus.CANCELLED);
    }

    public UserDTO getUser(UUID id) {
        var user = userRepository.findByIdAndStatusNot(id, UserStatus.CANCELLED).orElseThrow();
        return userMapper.toUserDTO(user);
    }


}
