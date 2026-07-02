package com.example.Robomain.application.chat.command;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SendMessageCommand {
    @NotNull private UUID conversationId;
    @NotNull private UUID senderId;
    @NotBlank private String content;
    private String messageType;
    private String clientMessageId;
}
