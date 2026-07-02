package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.ChatConversationJpaEntity;

@Repository
public interface ChatConversationJpaRepository extends JpaRepository<ChatConversationJpaEntity, UUID> {

    @Query("SELECT c FROM ChatConversationJpaEntity c WHERE " +
           "c.enterpriseId = :enterpriseId AND c.deleted = false ORDER BY c.createdAt DESC")
    List<ChatConversationJpaEntity> findByEnterpriseId(@Param("enterpriseId") UUID enterpriseId, Pageable pageable);

    @Query("SELECT COUNT(c) FROM ChatConversationJpaEntity c WHERE c.enterpriseId = :enterpriseId AND c.deleted = false")
    long countByEnterpriseId(@Param("enterpriseId") UUID enterpriseId);
}
