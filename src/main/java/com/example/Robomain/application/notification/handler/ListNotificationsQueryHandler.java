package com.example.Robomain.application.notification.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.notification.dto.NotificationDto;
import com.example.Robomain.application.notification.mapper.NotificationDtoMapper;
import com.example.Robomain.application.notification.query.ListNotificationsQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.notification.repository.INotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListNotificationsQueryHandler {

    private final INotificationRepository notificationRepository;
    private final NotificationDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<NotificationDto> handle(ListNotificationsQuery query) {
        var items = notificationRepository.findByUserId(query.getUserId(), query.isUnreadOnly(),
                query.getPage(), query.getSize());
        var total = notificationRepository.countByUserId(query.getUserId(), query.isUnreadOnly());
        var dtos = items.stream().map(dtoMapper::toDto).toList();
        return PaginationResponse.of(dtos, total, query.getPage(), query.getSize());
    }
}

