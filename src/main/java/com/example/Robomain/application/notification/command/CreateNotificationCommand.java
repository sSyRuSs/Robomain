package com.example.Robomain.application.notification.command;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateNotificationCommand {
    @NotNull private UUID userId;
    @NotBlank private String title;
    @NotBlank private String message;
    @NotBlank private String type;
    private UUID referenceId;
    private String referenceType;
    private String priority;
    private String link;
    private UUID actorId;
}
