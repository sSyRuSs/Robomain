package com.example.Robomain.application.user.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.user.command.UpdateUserCommand;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.user.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateUserCommandHandler {

    private final IUserRepository userRepository;

    @Transactional
    public UUID handle(UpdateUserCommand command) {
        var user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", command.getUserId()));

        user.updateProfile(command.getUsername(), command.getPhone(), command.getAddress());
        var saved = userRepository.save(user);
        return saved.getId();
    }
}
