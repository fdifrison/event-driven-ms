package com.fdifrison.user;

import com.fdifrison.common.registration.command.CreateUserCommand;
import com.fdifrison.common.registration.dto.UserDTO;

import com.fdifrison.common.registration.event.UserCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    User toUser(UserDTO userDTO);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    User toUser(UserCreatedEvent event);
    UserDTO toUserDTO(User user);

    CreateUserCommand toCreateUserCommand(UserDTO user);
    UserCreatedEvent toUserCreatedEvent(CreateUserCommand user);
    UserAggregate toUserAggregate(UserCreatedEvent event);
}
