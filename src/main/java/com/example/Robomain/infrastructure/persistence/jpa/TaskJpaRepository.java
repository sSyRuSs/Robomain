package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumStatus;
import com.example.Robomain.infrastructure.persistence.entity.TaskJpaEntity;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskJpaEntity, UUID> {

    @Query("SELECT t FROM TaskJpaEntity t WHERE " +
           "(:keyword IS NULL OR LOWER(t.taskName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:status IS NULL OR t.status = :status) AND " +
           "(:workOrderId IS NULL OR t.workOrderId = :workOrderId) AND " +
           "(:userId IS NULL OR t.userId = :userId)")
    List<TaskJpaEntity> search(
            @Param("keyword") String keyword,
            @Param("status") EnumStatus status,
            @Param("workOrderId") UUID workOrderId,
            @Param("userId") UUID userId,
            Pageable pageable);

    @Query("SELECT COUNT(t) FROM TaskJpaEntity t WHERE " +
           "(:keyword IS NULL OR LOWER(t.taskName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:status IS NULL OR t.status = :status) AND " +
           "(:workOrderId IS NULL OR t.workOrderId = :workOrderId) AND " +
           "(:userId IS NULL OR t.userId = :userId)")
    long countSearch(
            @Param("keyword") String keyword,
            @Param("status") EnumStatus status,
            @Param("workOrderId") UUID workOrderId,
            @Param("userId") UUID userId);
}
