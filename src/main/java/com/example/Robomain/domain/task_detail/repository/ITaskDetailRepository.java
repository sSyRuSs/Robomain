package com.example.Robomain.domain.task_detail.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.task_detail.model.TaskDetail;

public interface ITaskDetailRepository {
    Optional<TaskDetail> findById(UUID id);
    List<TaskDetail> findByTaskId(UUID taskId, int page, int size);
    long countByTaskId(UUID taskId);
    TaskDetail save(TaskDetail taskDetail);
    void deleteById(UUID id);
}
