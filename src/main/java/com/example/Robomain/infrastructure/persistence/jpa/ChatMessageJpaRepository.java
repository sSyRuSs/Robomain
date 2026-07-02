package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.ChatMessageJpaEntity;

@Repository
public interface ChatMessageJpaRepository extends JpaRepository<ChatMessageJpaEntity, UUID> {

    @Query("SELECT m FROM ChatMessageJpaEntity m WHERE m.conversationId = :cid AND m.deleted = false ORDER BY m.createdAt DESC")
    List<ChatMessageJpaEntity> findByConversationId(@Param("cid") UUID conversationId, Pageable pageable);

    @Query("SELECT COUNT(m) FROM ChatMessageJpaEntity m WHERE m.conversationId = :cid AND m.deleted = false")
    long countByConversationId(@Param("cid") UUID conversationId);
}
