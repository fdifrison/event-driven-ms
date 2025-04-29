package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserDTO;
import com.fdifrison.common.registration.dto.UserStatus;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    public UserDTO createUser(UserDTO userDTO) {
        var user = userMapper.toUser(userDTO).setStatus(UserStatus.PENDING);
        var saved = userRepository.save(user);
        return userMapper.toUserDTO(saved);
    }

    @Transactional
    public UserDTO deleteUser(UUID id) {
        var user = userRepository.findById(id).orElseThrow();
        user.setStatus(UserStatus.CANCELLED);
        return userMapper.toUserDTO(user);
    }

    public UserDTO getUser(UUID id) {
        var user = userRepository.findByIdAndStatusNot(id, UserStatus.CANCELLED).orElseThrow();
        return userMapper.toUserDTO(user);
    }
}
