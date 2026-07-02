package com.example.Robomain.application.notification.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.notification.command.MarkNotificationAsReadCommand;
import com.example.Robomain.application.notification.mapper.NotificationDtoMapper;
import com.example.Robomain.domain.notification.repository.INotificationRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarkNotificationAsReadCommandHandler {

    private final INotificationRepository notificationRepository;
    private final NotificationDtoMapper dtoMapper;

    @Transactional
    public UUID handle(MarkNotificationAsReadCommand cmd) {
        var n = notificationRepository.findById(cmd.getNotificationId())
                .orElseThrow(() -> new ResourceNotFoundException("Notification", cmd.getNotificationId()));
        n.markAsRead();
        return notificationRepository.save(n).getId();
    }
}
