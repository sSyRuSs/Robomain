package com.example.Robomain.application.chat.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.chat.dto.ChatMessageDto;
import com.example.Robomain.domain.chat.model.ChatMessage;

@Component
public class ChatMessageDtoMapper {

    public ChatMessageDto toDto(ChatMessage m) {
        return ChatMessageDto.builder()
                .id(m.getId()).conversationId(m.getConversationId()).senderId(m.getSenderId())
                .content(m.getContent()).messageType(m.getMessageType())
                .clientMessageId(m.getClientMessageId()).deleted(m.isDeleted()).pinned(m.isPinned())
                .createdAt(m.getCreatedAt())
                .build();
    }
}
