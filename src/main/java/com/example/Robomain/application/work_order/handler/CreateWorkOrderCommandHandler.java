package com.example.Robomain.application.work_order.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.work_order.command.CreateWorkOrderCommand;
import com.example.Robomain.application.work_order.mapper.WorkOrderDtoMapper;
import com.example.Robomain.domain.work_order.event.WorkOrderCreatedEvent;
import com.example.Robomain.domain.work_order.model.WorkOrder;
import com.example.Robomain.domain.work_order.repository.IWorkOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateWorkOrderCommandHandler {

    private final IWorkOrderRepository workOrderRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final WorkOrderDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateWorkOrderCommand command) {
        WorkOrder workOrder = WorkOrder.create(
                command.getWorkOrderName(), command.getAssetId(), command.getMaintenanceId());

        if (command.getWorkOrderLocation() != null || command.getWorkOrderDescription() != null
                || command.getSuggestStartDate() != null || command.getSuggestCompleteDate() != null) {
            workOrder.update(null, command.getWorkOrderLocation(), command.getWorkOrderDescription(),
                    null, null, command.getSuggestStartDate(), command.getSuggestCompleteDate());
        }
        if (command.getPriority() != null) workOrder.changePriority(command.getPriority());

        WorkOrder saved = workOrderRepository.save(workOrder);
        eventPublisher.publishEvent(
                new WorkOrderCreatedEvent(saved.getId(), saved.getWorkOrderName(), saved.getMaintenanceId()));
        return saved.getId();
    }
}
