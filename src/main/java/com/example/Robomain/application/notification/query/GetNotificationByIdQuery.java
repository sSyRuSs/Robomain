package com.example.Robomain.application.notification.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetNotificationByIdQuery {
    private final UUID notificationId;
}
