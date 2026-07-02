package com.example.Robomain.application.preventive_maintenance.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.preventive_maintenance.dto.PreventiveMaintenanceScheduleDto;
import com.example.Robomain.domain.preventive_maintenance.model.PreventiveMaintenanceSchedule;

@Component
public class PreventiveMaintenanceScheduleDtoMapper {

    public PreventiveMaintenanceScheduleDto toDto(PreventiveMaintenanceSchedule s) {
        return PreventiveMaintenanceScheduleDto.builder()
                .id(s.getId()).scheduleName(s.getScheduleName()).description(s.getDescription())
                .assetId(s.getAssetId()).maintenanceId(s.getMaintenanceId())
                .frequency(s.getFrequency()).intervalCount(s.getIntervalCount())
                .nextDueDate(s.getNextDueDate()).lastGeneratedAt(s.getLastGeneratedAt())
                .estimatedDurationHours(s.getEstimatedDurationHours()).leadTimeDays(s.getLeadTimeDays())
                .status(s.getStatus()).enterpriseId(s.getEnterpriseId())
                .build();
    }
}
