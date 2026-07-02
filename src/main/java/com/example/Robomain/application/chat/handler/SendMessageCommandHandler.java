package com.example.Robomain.application.chat.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.chat.command.SendMessageCommand;
import com.example.Robomain.application.chat.mapper.ChatMessageDtoMapper;
import com.example.Robomain.domain.chat.model.ChatMessage;
import com.example.Robomain.domain.chat.repository.IChatMessageRepository;
import com.example.Robomain.domain.chat.repository.IConversationRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SendMessageCommandHandler {

    private final IChatMessageRepository messageRepository;
    private final IConversationRepository conversationRepository;
    private final ChatMessageDtoMapper dtoMapper;

    @Transactional
    public UUID handle(SendMessageCommand cmd) {
        var conversation = conversationRepository.findById(cmd.getConversationId())
                .orElseThrow(() -> new ResourceNotFoundException("ChatConversation", cmd.getConversationId()));
        if (!conversation.isCanSendMessages()) {
            throw new ValidationException("Messaging is locked in this conversation");
        }
        ChatMessage msg = ChatMessage.send(cmd.getConversationId(), cmd.getSenderId(),
                cmd.getContent(), cmd.getMessageType(), cmd.getClientMessageId());
        return messageRepository.save(msg).getId();
    }
}
