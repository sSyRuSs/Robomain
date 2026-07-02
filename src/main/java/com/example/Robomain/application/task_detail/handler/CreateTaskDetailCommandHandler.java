package com.example.Robomain.application.task_detail.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.task_detail.command.CreateTaskDetailCommand;
import com.example.Robomain.application.task_detail.mapper.TaskDetailDtoMapper;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.task.repository.ITaskRepository;
import com.example.Robomain.domain.task_detail.model.TaskDetail;
import com.example.Robomain.domain.task_detail.repository.ITaskDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateTaskDetailCommandHandler {

    private final ITaskDetailRepository taskDetailRepository;
    private final ITaskRepository taskRepository;
    private final TaskDetailDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateTaskDetailCommand command) {
        // Guard: task must exist
        taskRepository.findById(command.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task", command.getTaskId()));

        TaskDetail saved = taskDetailRepository.save(TaskDetail.create(
                command.getTaskId(), command.getTaskDetailCode(),
                command.getTaskDetailProblem(), command.getTaskDetailDescription()));
        return saved.getId();
    }
}
