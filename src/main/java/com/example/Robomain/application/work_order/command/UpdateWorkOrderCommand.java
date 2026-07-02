package com.example.Robomain.application.work_order.command;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateWorkOrderCommand {
    private UUID workOrderId;
    private String workOrderName;
    private String workOrderLocation;
    private String workOrderDescription;
    private Date startDate;
    private Date completeDate;
    private Date suggestStartDate;
    private Date suggestCompleteDate;
    private EnumStatus status;
    private EnumPriority priority;
    private Boolean star; // null = no change
}
