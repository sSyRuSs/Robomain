package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.preventive_maintenance.model.PreventiveMaintenanceSchedule;
import com.example.Robomain.infrastructure.persistence.entity.PreventiveMaintenanceScheduleJpaEntity;

@Component
public class PreventiveMaintenanceScheduleMapper {

    public PreventiveMaintenanceSchedule toDomain(PreventiveMaintenanceScheduleJpaEntity e) {
        if (e == null) return null;
        return PreventiveMaintenanceSchedule.builder()
                .id(e.getId()).scheduleName(e.getScheduleName()).description(e.getDescription())
                .assetId(e.getAssetId()).maintenanceId(e.getMaintenanceId()).frequency(e.getFrequency())
                .intervalCount(e.getIntervalCount()).nextDueDate(e.getNextDueDate())
                .lastGeneratedAt(e.getLastGeneratedAt()).estimatedDurationHours(e.getEstimatedDurationHours())
                .leadTimeDays(e.getLeadTimeDays()).status(e.getStatus()).enterpriseId(e.getEnterpriseId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public PreventiveMaintenanceScheduleJpaEntity toJpa(PreventiveMaintenanceSchedule d) {
        if (d == null) return null;
        PreventiveMaintenanceScheduleJpaEntity e = PreventiveMaintenanceScheduleJpaEntity.builder()
                .scheduleName(d.getScheduleName()).description(d.getDescription())
                .assetId(d.getAssetId()).maintenanceId(d.getMaintenanceId()).frequency(d.getFrequency())
                .intervalCount(d.getIntervalCount()).nextDueDate(d.getNextDueDate())
                .lastGeneratedAt(d.getLastGeneratedAt()).estimatedDurationHours(d.getEstimatedDurationHours())
                .leadTimeDays(d.getLeadTimeDays()).status(d.getStatus()).enterpriseId(d.getEnterpriseId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
