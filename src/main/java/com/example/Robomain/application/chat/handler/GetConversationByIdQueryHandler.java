package com.example.Robomain.application.chat.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.chat.dto.ChatConversationDto;
import com.example.Robomain.application.chat.mapper.ChatConversationDtoMapper;
import com.example.Robomain.application.chat.query.GetConversationByIdQuery;
import com.example.Robomain.domain.chat.repository.IConversationRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetConversationByIdQueryHandler {

    private final IConversationRepository conversationRepository;
    private final ChatConversationDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public ChatConversationDto handle(GetConversationByIdQuery query) {
        return conversationRepository.findById(query.getConversationId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("ChatConversation", query.getConversationId()));
    }
}

