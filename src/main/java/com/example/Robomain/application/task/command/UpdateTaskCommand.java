package com.example.Robomain.application.task.command;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateTaskCommand {
    private UUID taskId;
    private String taskName;
    private String taskLocation;
    private String taskDescription;
    private Date startDate;
    private Date completeDate;
    private Date suggestStartDate;
    private Date suggestCompleteDate;
    private EnumStatus status;
    private EnumPriority priority;
    private Boolean star;
    private UUID userId; // assign/reassign technician
}
