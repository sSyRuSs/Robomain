package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.notification.model.Notification;
import com.example.Robomain.infrastructure.persistence.entity.NotificationJpaEntity;

@Component
public class NotificationMapper {

    public Notification toDomain(NotificationJpaEntity e) {
        if (e == null) return null;
        return Notification.builder()
                .id(e.getId()).userId(e.getUserId()).title(e.getTitle()).message(e.getMessage())
                .type(e.getType()).isRead(e.isRead()).referenceId(e.getReferenceId())
                .referenceType(e.getReferenceType()).link(e.getLink()).priority(e.getPriority())
                .actorId(e.getActorId()).createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public NotificationJpaEntity toJpa(Notification d) {
        if (d == null) return null;
        NotificationJpaEntity e = NotificationJpaEntity.builder()
                .userId(d.getUserId()).title(d.getTitle()).message(d.getMessage()).type(d.getType())
                .isRead(d.isRead()).referenceId(d.getReferenceId()).referenceType(d.getReferenceType())
                .link(d.getLink()).priority(d.getPriority()).actorId(d.getActorId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
