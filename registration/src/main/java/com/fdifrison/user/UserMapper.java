package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
}
