package com.example.Robomain.application.task_detail.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.task_detail.dto.TaskDetailDto;
import com.example.Robomain.application.task_detail.mapper.TaskDetailDtoMapper;
import com.example.Robomain.application.task_detail.query.ListTaskDetailsQuery;
import com.example.Robomain.domain.task_detail.repository.ITaskDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListTaskDetailsQueryHandler {

    private final ITaskDetailRepository taskDetailRepository;
    private final TaskDetailDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<TaskDetailDto> handle(ListTaskDetailsQuery query) {
        var list = taskDetailRepository.findByTaskId(query.getTaskId(), query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = taskDetailRepository.countByTaskId(query.getTaskId());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

