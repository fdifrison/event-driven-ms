package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
}
