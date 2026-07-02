package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumStatus;
import com.example.Robomain.domain.task.model.Task;
import com.example.Robomain.domain.task.repository.ITaskRepository;
import com.example.Robomain.infrastructure.persistence.jpa.TaskJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.TaskMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements ITaskRepository {

    private final TaskJpaRepository jpaRepository;
    private final TaskMapper mapper;

    @Override public Optional<Task> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Task> search(String keyword, EnumStatus status, UUID workOrderId, UUID userId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return jpaRepository.search(keyword, status, workOrderId, userId, pageable)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public long count(String keyword, EnumStatus status, UUID workOrderId, UUID userId) {
        return jpaRepository.countSearch(keyword, status, workOrderId, userId);
    }

    @Override public Task save(Task t) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(t)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
