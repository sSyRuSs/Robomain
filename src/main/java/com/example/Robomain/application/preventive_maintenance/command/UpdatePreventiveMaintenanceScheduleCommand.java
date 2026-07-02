package com.example.Robomain.application.preventive_maintenance.command;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMaintenanceFrequency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdatePreventiveMaintenanceScheduleCommand {
    private UUID scheduleId;
    private String scheduleName;
    private String description;
    private EnumMaintenanceFrequency frequency;
    private int intervalCount;
    private Date nextDueDate;
    private int estimatedDurationHours;
    private int leadTimeDays;
}
