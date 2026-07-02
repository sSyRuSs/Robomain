package com.example.Robomain.application.task.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.task.command.CreateTaskCommand;
import com.example.Robomain.application.task.mapper.TaskDtoMapper;
import com.example.Robomain.domain.task.event.TaskCreatedEvent;
import com.example.Robomain.domain.task.model.Task;
import com.example.Robomain.domain.task.repository.ITaskRepository;
import com.example.Robomain.domain.work_order.repository.IWorkOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateTaskCommandHandler {

    private final ITaskRepository taskRepository;
    private final IWorkOrderRepository workOrderRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TaskDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateTaskCommand command) {
        Task task = Task.create(command.getTaskName(), command.getWorkOrderId(),
                command.getAssetId(), command.getMaintenanceId());

        if (command.getTaskLocation() != null || command.getTaskDescription() != null
                || command.getSuggestStartDate() != null || command.getSuggestCompleteDate() != null) {
            task.update(null, command.getTaskLocation(), command.getTaskDescription(),
                    null, null, command.getSuggestStartDate(), command.getSuggestCompleteDate());
        }
        if (command.getPriority() != null) task.changePriority(command.getPriority());
        if (command.getUserId() != null) task.assign(command.getUserId());

        Task saved = taskRepository.save(task);

        if (command.getWorkOrderId() != null) {
            workOrderRepository.incrementTaskTotal(command.getWorkOrderId());
        }

        eventPublisher.publishEvent(
                new TaskCreatedEvent(saved.getId(), saved.getTaskName(), saved.getWorkOrderId()));
        return saved.getId();
    }
}
