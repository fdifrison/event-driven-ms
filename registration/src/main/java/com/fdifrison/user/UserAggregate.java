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


    private UUID id;
    @AggregateIdentifier
    private String username;
    private String email;
    private UserStatus status;


    public UserAggregate() {
    }

    @CommandHandler
    public void handle(CreateUserCommand command) {
        AggregateLifecycle.apply(new UserCreatedEvent(command));
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.username = event.username();
        this.email = event.email();
        this.status = UserStatus.PENDING;
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
