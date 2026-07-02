package com.example.Robomain.application.work_order.command;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateWorkOrderCommand {
    @NotBlank(message = "Work order name is required")
    private String workOrderName;
    private String workOrderLocation;
    private String workOrderDescription;
    private Date suggestStartDate;
    private Date suggestCompleteDate;
    private EnumPriority priority;
    private UUID assetId;
    private UUID maintenanceId;
}
