package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.chat.model.ChatMessage;
import com.example.Robomain.domain.chat.repository.IChatMessageRepository;
import com.example.Robomain.infrastructure.persistence.jpa.ChatMessageJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.ChatMessageMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChatMessageRepositoryImpl implements IChatMessageRepository {

    private final ChatMessageJpaRepository jpaRepository;
    private final ChatMessageMapper mapper;

    @Override public Optional<ChatMessage> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatMessage> findByConversationId(UUID conversationId, int page, int size) {
        return jpaRepository.findByConversationId(conversationId, PageRequest.of(page, size))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long countByConversationId(UUID conversationId) {
        return jpaRepository.countByConversationId(conversationId);
    }

    @Override public ChatMessage save(ChatMessage m) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(m)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
