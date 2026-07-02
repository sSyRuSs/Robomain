package com.example.Robomain.domain.notification.model;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Notification domain entity — an in-app alert delivered to a specific user.
 * Supports reference links back to the triggering entity (asset, task, work order, etc.)
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    private UUID id;
    private UUID userId;      // recipient
    private String title;
    private String message;
    private String type;      // NotificationType value as string
    @Builder.Default
    private boolean isRead = false;
    private UUID referenceId;     // FK to the entity that triggered this
    private String referenceType; // "ASSET", "TASK", "WORK_ORDER", etc.
    private String link;          // deep-link path for frontend navigation
    private String priority;      // LOW, MEDIUM, HIGH, URGENT
    private UUID actorId;         // user who triggered the notification (optional)
    private Date createdAt;
    private Date updatedAt;

    public static Notification create(UUID userId, String title, String message, String type,
                                       UUID referenceId, String referenceType,
                                       String priority, String link, UUID actorId) {
        return Notification.builder()
                .id(UUID.randomUUID()).userId(userId).title(title).message(message).type(type)
                .isRead(false).referenceId(referenceId).referenceType(referenceType)
                .priority(priority).link(link).actorId(actorId)
                .build();
    }

    public void markAsRead() {
        this.isRead = true;
    }
}
