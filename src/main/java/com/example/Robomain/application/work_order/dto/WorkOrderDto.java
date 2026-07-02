package com.example.Robomain.application.work_order.dto;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class WorkOrderDto {
    private UUID id;
    private String workOrderName;
    private String workOrderLocation;
    private String workOrderDescription;
    private Date startDate;
    private Date completeDate;
    private Date suggestStartDate;
    private Date suggestCompleteDate;
    private EnumStatus status;
    private EnumPriority priority;
    private boolean star;
    private int taskTotal;
    private UUID assetId;
    private UUID maintenanceId;
}
