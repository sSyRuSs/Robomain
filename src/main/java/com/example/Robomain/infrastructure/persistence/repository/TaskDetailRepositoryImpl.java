package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.task_detail.model.TaskDetail;
import com.example.Robomain.domain.task_detail.repository.ITaskDetailRepository;
import com.example.Robomain.infrastructure.persistence.jpa.TaskDetailJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.TaskDetailMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TaskDetailRepositoryImpl implements ITaskDetailRepository {

    private final TaskDetailJpaRepository jpaRepository;
    private final TaskDetailMapper mapper;

    @Override public Optional<TaskDetail> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override public List<TaskDetail> findByTaskId(UUID taskId, int page, int size) {
        return jpaRepository.findByTaskIdOrderByCreatedAtDesc(
                        taskId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long countByTaskId(UUID taskId) {
        return jpaRepository.countByTaskId(taskId);
    }

    @Override public TaskDetail save(TaskDetail d) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(d)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
