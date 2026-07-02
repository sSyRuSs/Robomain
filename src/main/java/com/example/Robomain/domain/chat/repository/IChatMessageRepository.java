package com.example.Robomain.domain.chat.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.chat.model.ChatMessage;

public interface IChatMessageRepository {
    Optional<ChatMessage> findById(UUID id);
    List<ChatMessage> findByConversationId(UUID conversationId, int page, int size);
    long countByConversationId(UUID conversationId);
    ChatMessage save(ChatMessage message);
    void deleteById(UUID id);
}
