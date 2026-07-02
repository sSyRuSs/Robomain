package com.example.Robomain.application.notification.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.notification.dto.NotificationDto;
import com.example.Robomain.domain.notification.model.Notification;

@Component
public class NotificationDtoMapper {

    public NotificationDto toDto(Notification n) {
        return NotificationDto.builder()
                .id(n.getId()).userId(n.getUserId()).title(n.getTitle()).message(n.getMessage())
                .type(n.getType()).isRead(n.isRead()).referenceId(n.getReferenceId())
                .referenceType(n.getReferenceType()).link(n.getLink()).priority(n.getPriority())
                .actorId(n.getActorId()).createdAt(n.getCreatedAt())
                .build();
    }
}
