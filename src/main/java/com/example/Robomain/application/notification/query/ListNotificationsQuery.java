package com.example.Robomain.application.notification.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ListNotificationsQuery {
    private final UUID userId;
    private final boolean unreadOnly;
    private final int page;
    private final int size;
}
