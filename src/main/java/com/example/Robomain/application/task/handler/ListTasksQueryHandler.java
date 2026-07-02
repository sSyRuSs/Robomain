package com.example.Robomain.application.task.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.task.dto.TaskDto;
import com.example.Robomain.application.task.mapper.TaskDtoMapper;
import com.example.Robomain.application.task.query.ListTasksQuery;
import com.example.Robomain.domain.task.repository.ITaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListTasksQueryHandler {

    private final ITaskRepository taskRepository;
    private final TaskDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<TaskDto> handle(ListTasksQuery query) {
        var list = taskRepository.search(
                        query.getSearch(), query.getStatus(),
                        query.getWorkOrderId(), query.getUserId(),
                        query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = taskRepository.count(
                query.getSearch(), query.getStatus(), query.getWorkOrderId(), query.getUserId());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

