package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.chat.model.ChatConversation;
import com.example.Robomain.domain.chat.repository.IConversationRepository;
import com.example.Robomain.infrastructure.persistence.jpa.ChatConversationJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.ChatConversationMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChatConversationRepositoryImpl implements IConversationRepository {

    private final ChatConversationJpaRepository jpaRepository;
    private final ChatConversationMapper mapper;

    @Override public Optional<ChatConversation> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatConversation> findByEnterpriseId(UUID enterpriseId, int page, int size) {
        return jpaRepository.findByEnterpriseId(enterpriseId, PageRequest.of(page, size))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long countByEnterpriseId(UUID enterpriseId) {
        return jpaRepository.countByEnterpriseId(enterpriseId);
    }

    @Override public ChatConversation save(ChatConversation c) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(c)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
