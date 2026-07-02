package com.example.Robomain.application.task.dto;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class TaskDto {
    private UUID id;
    private String taskName;
    private String taskLocation;
    private String taskDescription;
    private Date startDate;
    private Date completeDate;
    private Date suggestStartDate;
    private Date suggestCompleteDate;
    private EnumStatus status;
    private EnumPriority priority;
    private boolean star;
    private int issueNumber;
    private UUID assetId;
    private UUID workOrderId;
    private UUID maintenanceId;
    private UUID userId;
}
