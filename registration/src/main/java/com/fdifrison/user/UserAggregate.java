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
    public UserAggregate(CreateUserCommand command, UserRepository userRepository, UserMapper userMapper) {
        var user = userRepository.findByEmailAndStatusNot(command.email(), UserStatus.CANCELLED);
        if (user.isPresent()) {
            throw new RuntimeException(String.format("User with email %s already exists", command.email()));
        }
        var event = userMapper.toUserCreatedEvent(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.username = event.username();
        this.email = event.email();
        this.status = UserStatus.PENDING;
    }



    @CommandHandler
    public void handle(UserRepository userRepository, DeleteUserCommand command) {
        var user = userRepository
                .findByIdAndStatusNot(command.id(), UserStatus.CANCELLED)
                .orElseThrow(() -> new RuntimeException("No valid user found for id: " + command.id()));
        AggregateLifecycle.apply(new UserDeletedEvent(user.getId()));

    }

    @EventSourcingHandler
    public void on(UserDeletedEvent event) {
        this.id = event.id();
    }



}
