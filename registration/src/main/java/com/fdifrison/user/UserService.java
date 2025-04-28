package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO userDTO) {
        var saved = userRepository.save(userMapper.toUser(userDTO));
        return userMapper.toUserDTO(saved);
    }
}
