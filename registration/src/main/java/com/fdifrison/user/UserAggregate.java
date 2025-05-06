package com.fdifrison.user;

import com.fdifrison.common.registration.command.CreateUserCommand;
import com.fdifrison.common.registration.command.DeleteUserCommand;
import com.fdifrison.common.registration.dto.UserStatus;
import com.fdifrison.common.registration.event.UserCreatedEvent;
import com.fdifrison.common.registration.event.UserDeletedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
class UserAggregate {

    @AggregateIdentifier
    private UUID id;
    private String username;
    private String email;
    private UserStatus status;


    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(CreateUserCommand command, UserService userService) {
        var user = userService.createUser(command.setStatus(UserStatus.PENDING));
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.id = event.getId();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.status = event.getStatus();
    }


    @CommandHandler
    public void handle(DeleteUserCommand command) {
        AggregateLifecycle.apply(new UserDeletedEvent(command.id()));

    }

    @EventSourcingHandler
    public void on(UserDeletedEvent event) {
        this.id = event.id();
    }


}
