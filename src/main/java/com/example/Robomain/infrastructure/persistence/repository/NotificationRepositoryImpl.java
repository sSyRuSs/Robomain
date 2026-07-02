package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.domain.notification.model.Notification;
import com.example.Robomain.domain.notification.repository.INotificationRepository;
import com.example.Robomain.infrastructure.persistence.jpa.NotificationJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.NotificationMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements INotificationRepository {

    private final NotificationJpaRepository jpaRepository;
    private final NotificationMapper mapper;

    @Override public Optional<Notification> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Notification> findByUserId(UUID userId, boolean unreadOnly, int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        var list = unreadOnly
                ? jpaRepository.findByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId, pageable)
                : jpaRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
        return list.stream().map(mapper::toDomain).toList();
    }

    @Override
    public long countByUserId(UUID userId, boolean unreadOnly) {
        return unreadOnly ? jpaRepository.countByUserIdAndIsReadFalse(userId) : jpaRepository.countByUserId(userId);
    }

    @Override
    @Transactional
    public void markAllAsReadByUserId(UUID userId) {
        jpaRepository.markAllAsReadByUserId(userId);
    }

    @Override public Notification save(Notification n) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(n)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
