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
    UserView toUserView(UserCreatedEvent event);
    @Mapping(target = "id", ignore = true)
    User toUser(CreateUserCommand command);
    UserDTO toUserDTO(BaseUser<?> user);


}
