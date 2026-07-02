package com.example.Robomain.application.work_order.handler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.work_order.command.UpdateWorkOrderCommand;
import com.example.Robomain.application.work_order.mapper.WorkOrderDtoMapper;
import com.example.Robomain.domain.work_order.repository.IWorkOrderRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateWorkOrderCommandHandler {

    private final IWorkOrderRepository workOrderRepository;
    private final WorkOrderDtoMapper dtoMapper;

    @CacheEvict(value = "work_order", key = "#command.workOrderId")
    @Transactional
    public UUID handle(UpdateWorkOrderCommand command) {
        var workOrder = workOrderRepository.findById(command.getWorkOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("WorkOrder", command.getWorkOrderId()));

        workOrder.update(command.getWorkOrderName(), command.getWorkOrderLocation(),
                command.getWorkOrderDescription(), command.getStartDate(), command.getCompleteDate(),
                command.getSuggestStartDate(), command.getSuggestCompleteDate());

        if (command.getStatus() != null) workOrder.changeStatus(command.getStatus());
        if (command.getPriority() != null) workOrder.changePriority(command.getPriority());
        if (Boolean.TRUE.equals(command.getStar()) != workOrder.isStar()) workOrder.toggleStar();

        return workOrderRepository.save(workOrder).getId();
    }
}
