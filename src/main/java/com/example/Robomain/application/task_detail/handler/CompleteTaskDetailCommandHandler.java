package com.example.Robomain.application.task_detail.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.task_detail.command.CompleteTaskDetailCommand;
import com.example.Robomain.application.task_detail.mapper.TaskDetailDtoMapper;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.task_detail.repository.ITaskDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompleteTaskDetailCommandHandler {

    private final ITaskDetailRepository taskDetailRepository;
    private final TaskDetailDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CompleteTaskDetailCommand command) {
        var detail = taskDetailRepository.findById(command.getTaskDetailId())
                .orElseThrow(() -> new ResourceNotFoundException("TaskDetail", command.getTaskDetailId()));
        detail.complete();
        return taskDetailRepository.save(detail).getId();
    }
}
