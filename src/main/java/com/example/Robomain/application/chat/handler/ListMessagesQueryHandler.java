package com.example.Robomain.application.chat.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.chat.dto.ChatMessageDto;
import com.example.Robomain.application.chat.mapper.ChatMessageDtoMapper;
import com.example.Robomain.application.chat.query.ListMessagesQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.chat.repository.IChatMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListMessagesQueryHandler {

    private final IChatMessageRepository messageRepository;
    private final ChatMessageDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<ChatMessageDto> handle(ListMessagesQuery query) {
        var messages = messageRepository.findByConversationId(query.getConversationId(),
                query.getPage(), query.getSize());
        var total = messageRepository.countByConversationId(query.getConversationId());
        var dtos = messages.stream().map(dtoMapper::toDto).toList();
        return PaginationResponse.of(dtos, total, query.getPage(), query.getSize());
    }
}

