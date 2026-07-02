package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.chat.model.ChatMessage;
import com.example.Robomain.infrastructure.persistence.entity.ChatMessageJpaEntity;

@Component
public class ChatMessageMapper {

    public ChatMessage toDomain(ChatMessageJpaEntity e) {
        if (e == null) return null;
        return ChatMessage.builder()
                .id(e.getId()).conversationId(e.getConversationId()).senderId(e.getSenderId())
                .content(e.getContent()).messageType(e.getMessageType())
                .clientMessageId(e.getClientMessageId())
                .deleted(Boolean.TRUE.equals(e.getDeleted())).pinned(Boolean.TRUE.equals(e.getPinned()))
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public ChatMessageJpaEntity toJpa(ChatMessage d) {
        if (d == null) return null;
        ChatMessageJpaEntity e = ChatMessageJpaEntity.builder()
                .conversationId(d.getConversationId()).senderId(d.getSenderId())
                .content(d.getContent()).messageType(d.getMessageType())
                .clientMessageId(d.getClientMessageId()).deleted(d.isDeleted()).pinned(d.isPinned())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
