package com.example.Robomain.application.task.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.task.dto.TaskDto;
import com.example.Robomain.application.task.mapper.TaskDtoMapper;
import com.example.Robomain.application.task.query.GetTaskByIdQuery;
import com.example.Robomain.domain.task.repository.ITaskRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetTaskByIdQueryHandler {

    private final ITaskRepository taskRepository;
    private final TaskDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public TaskDto handle(GetTaskByIdQuery query) {
        return taskRepository.findById(query.getTaskId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Task", query.getTaskId()));
    }
}

