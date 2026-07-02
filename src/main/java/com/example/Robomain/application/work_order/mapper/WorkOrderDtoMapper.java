package com.example.Robomain.application.work_order.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.work_order.dto.WorkOrderDto;
import com.example.Robomain.domain.work_order.model.WorkOrder;

@Component
public class WorkOrderDtoMapper {

    public WorkOrderDto toDto(WorkOrder w) {
        return WorkOrderDto.builder()
                .id(w.getId()).workOrderName(w.getWorkOrderName())
                .workOrderLocation(w.getWorkOrderLocation())
                .workOrderDescription(w.getWorkOrderDescription())
                .startDate(w.getStartDate()).completeDate(w.getCompleteDate())
                .suggestStartDate(w.getSuggestStartDate()).suggestCompleteDate(w.getSuggestCompleteDate())
                .status(w.getStatus()).priority(w.getPriority())
                .star(w.isStar()).taskTotal(w.getTaskTotal())
                .assetId(w.getAssetId()).maintenanceId(w.getMaintenanceId())
                .build();
    }
}
