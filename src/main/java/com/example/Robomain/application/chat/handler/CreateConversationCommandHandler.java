package com.example.Robomain.application.chat.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.chat.command.CreateConversationCommand;
import com.example.Robomain.application.chat.mapper.ChatConversationDtoMapper;
import com.example.Robomain.domain.chat.model.ChatConversation;
import com.example.Robomain.domain.chat.repository.IConversationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateConversationCommandHandler {

    private final IConversationRepository conversationRepository;
    private final ChatConversationDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateConversationCommand cmd) {
        ChatConversation c = ChatConversation.create(cmd.getType(), cmd.getName(),
                cmd.getDepartmentId(), cmd.getEnterpriseId(), cmd.getCreatedBy());
        return conversationRepository.save(c).getId();
    }
}
