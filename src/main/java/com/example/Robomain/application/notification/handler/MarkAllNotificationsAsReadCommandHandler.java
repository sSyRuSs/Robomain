package com.example.Robomain.application.notification.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.notification.command.MarkAllNotificationsAsReadCommand;
import com.example.Robomain.domain.notification.repository.INotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarkAllNotificationsAsReadCommandHandler {

    private final INotificationRepository notificationRepository;

    @Transactional
    public void handle(MarkAllNotificationsAsReadCommand cmd) {
        notificationRepository.markAllAsReadByUserId(cmd.getUserId());
    }
}
