package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.chat.model.ChatConversation;
import com.example.Robomain.infrastructure.persistence.entity.ChatConversationJpaEntity;

@Component
public class ChatConversationMapper {

    public ChatConversation toDomain(ChatConversationJpaEntity e) {
        if (e == null) return null;
        return ChatConversation.builder()
                .id(e.getId()).type(e.getType()).name(e.getName()).departmentId(e.getDepartmentId())
                .enterpriseId(e.getEnterpriseId()).createdBy(e.getCreatedBy())
                .deleted(Boolean.TRUE.equals(e.getDeleted())).canSendMessages(Boolean.TRUE.equals(e.getCanSendMessages()))
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public ChatConversationJpaEntity toJpa(ChatConversation d) {
        if (d == null) return null;
        ChatConversationJpaEntity e = ChatConversationJpaEntity.builder()
                .type(d.getType()).name(d.getName()).departmentId(d.getDepartmentId())
                .enterpriseId(d.getEnterpriseId()).createdBy(d.getCreatedBy())
                .deleted(d.isDeleted()).canSendMessages(d.isCanSendMessages())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
