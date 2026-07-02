package com.example.Robomain.domain.notification.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.notification.model.Notification;

public interface INotificationRepository {
    Optional<Notification> findById(UUID id);
    List<Notification> findByUserId(UUID userId, boolean unreadOnly, int page, int size);
    long countByUserId(UUID userId, boolean unreadOnly);
    void markAllAsReadByUserId(UUID userId);
    Notification save(Notification notification);
    void deleteById(UUID id);
}
