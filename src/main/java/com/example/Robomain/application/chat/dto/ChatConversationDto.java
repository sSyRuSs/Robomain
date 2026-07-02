package com.example.Robomain.application.chat.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class ChatConversationDto {
    private UUID id;
    private String type;
    private String name;
    private UUID departmentId;
    private UUID enterpriseId;
    private UUID createdBy;
    private boolean deleted;
    private boolean canSendMessages;
}
