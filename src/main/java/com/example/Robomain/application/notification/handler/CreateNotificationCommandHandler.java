package com.example.Robomain.application.notification.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.notification.command.CreateNotificationCommand;
import com.example.Robomain.application.notification.mapper.NotificationDtoMapper;
import com.example.Robomain.domain.notification.model.Notification;
import com.example.Robomain.domain.notification.repository.INotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateNotificationCommandHandler {

    private final INotificationRepository notificationRepository;
    private final NotificationDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateNotificationCommand cmd) {
        Notification n = Notification.create(cmd.getUserId(), cmd.getTitle(), cmd.getMessage(),
                cmd.getType(), cmd.getReferenceId(), cmd.getReferenceType(),
                cmd.getPriority(), cmd.getLink(), cmd.getActorId());
        return notificationRepository.save(n).getId();
    }
}
