package com.example.Robomain.application.preventive_maintenance.command;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMaintenanceFrequency;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreatePreventiveMaintenanceScheduleCommand {
    @NotBlank private String scheduleName;
    private String description;
    @NotNull private UUID assetId;
    @NotNull private UUID maintenanceId;
    @NotNull private EnumMaintenanceFrequency frequency;
    @Min(1) private int intervalCount;
    @NotNull private Date nextDueDate;
    @Min(1) private int estimatedDurationHours;
    @Min(0) private int leadTimeDays;
    @NotNull private UUID enterpriseId;
}
