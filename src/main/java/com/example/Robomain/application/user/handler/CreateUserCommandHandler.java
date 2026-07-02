package com.example.Robomain.application.user.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.user.command.CreateUserCommand;
import com.example.Robomain.domain.shared.exception.ConflictException;
import com.example.Robomain.domain.user.event.UserCreatedEvent;
import com.example.Robomain.domain.user.model.User;
import com.example.Robomain.domain.user.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserCommandHandler {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public UUID handle(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new ConflictException("User", "email", command.getEmail());
        }

        String hashedPassword = passwordEncoder.encode(command.getPassword());
        User user = User.create(command.getEmail(), command.getUsername(), hashedPassword);

        if (command.getPhone() != null) user.updateProfile(command.getUsername(), command.getPhone(), command.getAddress());

        User saved = userRepository.save(user);
        eventPublisher.publishEvent(new UserCreatedEvent(saved.getId(), saved.getEmail(), saved.getUsername()));
        return saved.getId();
    }
}
