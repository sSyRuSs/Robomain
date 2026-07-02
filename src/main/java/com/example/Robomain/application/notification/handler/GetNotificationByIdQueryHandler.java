package com.example.Robomain.application.notification.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.notification.dto.NotificationDto;
import com.example.Robomain.application.notification.mapper.NotificationDtoMapper;
import com.example.Robomain.application.notification.query.GetNotificationByIdQuery;
import com.example.Robomain.domain.notification.repository.INotificationRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetNotificationByIdQueryHandler {

    private final INotificationRepository notificationRepository;
    private final NotificationDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public NotificationDto handle(GetNotificationByIdQuery query) {
        return notificationRepository.findById(query.getNotificationId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", query.getNotificationId()));
    }
}

