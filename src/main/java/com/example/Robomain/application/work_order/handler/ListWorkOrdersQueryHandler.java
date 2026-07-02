package com.example.Robomain.application.work_order.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.work_order.dto.WorkOrderDto;
import com.example.Robomain.application.work_order.mapper.WorkOrderDtoMapper;
import com.example.Robomain.application.work_order.query.ListWorkOrdersQuery;
import com.example.Robomain.domain.work_order.repository.IWorkOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListWorkOrdersQueryHandler {

    private final IWorkOrderRepository workOrderRepository;
    private final WorkOrderDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<WorkOrderDto> handle(ListWorkOrdersQuery query) {
        var list = workOrderRepository.search(
                        query.getSearch(), query.getStatus(), query.getPriority(),
                        query.getMaintenanceId(), query.getAssetId(),
                        query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = workOrderRepository.count(
                query.getSearch(), query.getStatus(), query.getPriority(),
                query.getMaintenanceId(), query.getAssetId());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

