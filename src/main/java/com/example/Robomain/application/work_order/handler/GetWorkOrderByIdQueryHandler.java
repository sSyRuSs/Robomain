package com.example.Robomain.application.work_order.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.work_order.dto.WorkOrderDto;
import com.example.Robomain.application.work_order.mapper.WorkOrderDtoMapper;
import com.example.Robomain.application.work_order.query.GetWorkOrderByIdQuery;
import com.example.Robomain.domain.work_order.repository.IWorkOrderRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetWorkOrderByIdQueryHandler {

    private final IWorkOrderRepository workOrderRepository;
    private final WorkOrderDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public WorkOrderDto handle(GetWorkOrderByIdQuery query) {
        return workOrderRepository.findById(query.getWorkOrderId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("WorkOrder", query.getWorkOrderId()));
    }
}

