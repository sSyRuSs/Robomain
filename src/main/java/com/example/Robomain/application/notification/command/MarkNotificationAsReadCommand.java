package com.example.Robomain.application.notification.command;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MarkNotificationAsReadCommand {
    private UUID notificationId;
}
