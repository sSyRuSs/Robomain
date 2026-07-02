package com.example.Robomain.application.chat.dto;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class ChatMessageDto {
    private UUID id;
    private UUID conversationId;
    private UUID senderId;
    private String content;
    private String messageType;
    private String clientMessageId;
    private boolean deleted;
    private boolean pinned;
    private Date createdAt;
}
