package com.example.Robomain.application.notification.dto;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class NotificationDto {
    private UUID id;
    private UUID userId;
    private String title;
    private String message;
    private String type;
    private boolean isRead;
    private UUID referenceId;
    private String referenceType;
    private String link;
    private String priority;
    private UUID actorId;
    private Date createdAt;
}
