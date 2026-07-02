package com.example.Robomain.application.chat.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.chat.dto.ChatConversationDto;
import com.example.Robomain.domain.chat.model.ChatConversation;

@Component
public class ChatConversationDtoMapper {

    public ChatConversationDto toDto(ChatConversation c) {
        return ChatConversationDto.builder()
                .id(c.getId()).type(c.getType()).name(c.getName()).departmentId(c.getDepartmentId())
                .enterpriseId(c.getEnterpriseId()).createdBy(c.getCreatedBy())
                .deleted(c.isDeleted()).canSendMessages(c.isCanSendMessages())
                .build();
    }
}
