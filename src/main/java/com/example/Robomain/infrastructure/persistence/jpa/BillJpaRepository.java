package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.BillJpaEntity;

@Repository
public interface BillJpaRepository extends JpaRepository<BillJpaEntity, UUID> {

    @Query("SELECT b FROM BillJpaEntity b WHERE " +
           "(:taskId IS NULL OR b.taskId = :taskId) " +
           "ORDER BY b.createdAt DESC")
    List<BillJpaEntity> findByTaskId(@Param("taskId") UUID taskId, Pageable pageable);

    @Query("SELECT COUNT(b) FROM BillJpaEntity b WHERE (:taskId IS NULL OR b.taskId = :taskId)")
    long countByTaskId(@Param("taskId") UUID taskId);
}
