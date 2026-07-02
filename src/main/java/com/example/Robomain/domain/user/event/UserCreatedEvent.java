package com.example.Robomain.domain.user.event;

import java.util.UUID;

/**
 * Domain event published when a new user is created.
 * Subscribers: notification service, audit log.
 */
public record UserCreatedEvent(UUID userId, String email, String username) {}
