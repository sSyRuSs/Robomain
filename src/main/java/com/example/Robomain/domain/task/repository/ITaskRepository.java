package com.example.Robomain.domain.task.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumStatus;
import com.example.Robomain.domain.task.model.Task;

public interface ITaskRepository {
    Optional<Task> findById(UUID id);
    List<Task> search(String keyword, EnumStatus status, UUID workOrderId, UUID userId, int page, int size);
    long count(String keyword, EnumStatus status, UUID workOrderId, UUID userId);
    Task save(Task task);
    void deleteById(UUID id);
}
