package com.example.Robomain.application.task.command;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPriority;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateTaskCommand {
    @NotBlank(message = "Task name is required")
    private String taskName;
    private String taskLocation;
    private String taskDescription;
    private Date suggestStartDate;
    private Date suggestCompleteDate;
    private EnumPriority priority;
    private UUID assetId;
    private UUID workOrderId;
    private UUID maintenanceId;
    private UUID userId;
}
