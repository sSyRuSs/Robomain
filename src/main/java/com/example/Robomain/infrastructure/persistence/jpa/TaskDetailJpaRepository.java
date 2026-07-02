package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.TaskDetailJpaEntity;

@Repository
public interface TaskDetailJpaRepository extends JpaRepository<TaskDetailJpaEntity, UUID> {

    List<TaskDetailJpaEntity> findByTaskIdOrderByCreatedAtDesc(UUID taskId, Pageable pageable);

    @Query("SELECT COUNT(d) FROM TaskDetailJpaEntity d WHERE d.taskId = :taskId")
    long countByTaskId(@Param("taskId") UUID taskId);
}
