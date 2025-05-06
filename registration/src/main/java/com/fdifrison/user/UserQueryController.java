package com.fdifrison.user;

import com.fdifrison.common.registration.dto.UserDTO;
import com.fdifrison.common.registration.query.GetUserQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
@Validated
class UserQueryController {

    private final QueryGateway queryGateway;

    UserQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("{id}")
    public UserDTO getUser(@PathVariable UUID id) {
        return queryGateway.query(new GetUserQuery(id), ResponseTypes.instanceOf(UserDTO.class)).join();
    }

}

