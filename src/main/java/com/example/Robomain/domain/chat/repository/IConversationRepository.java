package com.example.Robomain.domain.chat.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.chat.model.ChatConversation;

public interface IConversationRepository {
    Optional<ChatConversation> findById(UUID id);
    List<ChatConversation> findByEnterpriseId(UUID enterpriseId, int page, int size);
    long countByEnterpriseId(UUID enterpriseId);
    ChatConversation save(ChatConversation conversation);
    void deleteById(UUID id);
}
