package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.work_order.model.WorkOrder;
import com.example.Robomain.infrastructure.persistence.entity.WorkOrderJpaEntity;

@Component
public class WorkOrderMapper {

    public WorkOrder toDomain(WorkOrderJpaEntity e) {
        if (e == null) return null;
        return WorkOrder.builder()
                .id(e.getId()).workOrderName(e.getWorkOrderName())
                .workOrderLocation(e.getWorkOrderLocation())
                .workOrderDescription(e.getWorkOrderDescription())
                .startDate(e.getStartDate()).completeDate(e.getCompleteDate())
                .suggestStartDate(e.getSuggestStartDate()).suggestCompleteDate(e.getSuggestCompleteDate())
                .status(e.getStatus()).priority(e.getPriority())
                .star(e.isStar()).taskTotal(e.getTaskTotal())
                .assetId(e.getAssetId()).maintenanceId(e.getMaintenanceId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public WorkOrderJpaEntity toJpa(WorkOrder d) {
        if (d == null) return null;
        WorkOrderJpaEntity e = WorkOrderJpaEntity.builder()
                .workOrderName(d.getWorkOrderName()).workOrderLocation(d.getWorkOrderLocation())
                .workOrderDescription(d.getWorkOrderDescription())
                .startDate(d.getStartDate()).completeDate(d.getCompleteDate())
                .suggestStartDate(d.getSuggestStartDate()).suggestCompleteDate(d.getSuggestCompleteDate())
                .status(d.getStatus()).priority(d.getPriority())
                .star(d.isStar()).taskTotal(d.getTaskTotal())
                .assetId(d.getAssetId()).maintenanceId(d.getMaintenanceId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
