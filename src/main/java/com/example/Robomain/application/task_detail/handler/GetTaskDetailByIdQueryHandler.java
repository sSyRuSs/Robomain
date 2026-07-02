package com.example.Robomain.application.task_detail.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.task_detail.dto.TaskDetailDto;
import com.example.Robomain.application.task_detail.mapper.TaskDetailDtoMapper;
import com.example.Robomain.application.task_detail.query.GetTaskDetailByIdQuery;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.task_detail.repository.ITaskDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetTaskDetailByIdQueryHandler {

    private final ITaskDetailRepository taskDetailRepository;
    private final TaskDetailDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public TaskDetailDto handle(GetTaskDetailByIdQuery query) {
        return taskDetailRepository.findById(query.getTaskDetailId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("TaskDetail", query.getTaskDetailId()));
    }
}

