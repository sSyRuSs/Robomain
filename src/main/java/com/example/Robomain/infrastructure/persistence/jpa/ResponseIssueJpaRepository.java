package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.ResponseIssueJpaEntity;

@Repository
public interface ResponseIssueJpaRepository extends JpaRepository<ResponseIssueJpaEntity, UUID> {

    @Query("SELECT r FROM ResponseIssueJpaEntity r WHERE r.issueId = :issueId AND " +
           "(:parentId IS NULL AND r.parentResponseId IS NULL OR r.parentResponseId = :parentId) " +
           "ORDER BY r.createdAt ASC")
    List<ResponseIssueJpaEntity> findByIssueId(@Param("issueId") UUID issueId,
                                                @Param("parentId") UUID parentId,
                                                Pageable pageable);

    @Query("SELECT COUNT(r) FROM ResponseIssueJpaEntity r WHERE r.issueId = :issueId AND " +
           "(:parentId IS NULL AND r.parentResponseId IS NULL OR r.parentResponseId = :parentId)")
    long countByIssueId(@Param("issueId") UUID issueId, @Param("parentId") UUID parentId);
}
