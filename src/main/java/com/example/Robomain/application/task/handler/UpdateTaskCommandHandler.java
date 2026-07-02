package com.example.Robomain.application.task.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.task.command.UpdateTaskCommand;
import com.example.Robomain.application.task.mapper.TaskDtoMapper;
import com.example.Robomain.domain.task.repository.ITaskRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateTaskCommandHandler {

    private final ITaskRepository taskRepository;
    private final TaskDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateTaskCommand command) {
        var task = taskRepository.findById(command.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task", command.getTaskId()));

        task.update(command.getTaskName(), command.getTaskLocation(), command.getTaskDescription(),
                command.getStartDate(), command.getCompleteDate(),
                command.getSuggestStartDate(), command.getSuggestCompleteDate());

        if (command.getStatus() != null) task.changeStatus(command.getStatus());
        if (command.getPriority() != null) task.changePriority(command.getPriority());
        if (Boolean.TRUE.equals(command.getStar()) != task.isStar()) task.toggleStar();
        if (command.getUserId() != null) task.assign(command.getUserId());

        return taskRepository.save(task).getId();
    }
}
