package com.example.Robomain.application.preventive_maintenance.dto;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMaintenanceFrequency;
import com.example.Robomain.domain.shared.enums.EnumPreventiveScheduleStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class PreventiveMaintenanceScheduleDto {
    private UUID id;
    private String scheduleName;
    private String description;
    private UUID assetId;
    private UUID maintenanceId;
    private EnumMaintenanceFrequency frequency;
    private int intervalCount;
    private Date nextDueDate;
    private Date lastGeneratedAt;
    private int estimatedDurationHours;
    private int leadTimeDays;
    private EnumPreventiveScheduleStatus status;
    private UUID enterpriseId;
}
