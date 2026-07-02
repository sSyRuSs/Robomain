package com.example.Robomain.domain.task.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;
import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Task domain entity — a unit of work assigned to a technician within a WorkOrder.
 * Tracks issue count, assignment, and lifecycle status.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private UUID id;
    private String taskName;
    private String taskLocation;
    private String taskDescription;
    private Date startDate;
    private Date completeDate;
    private Date suggestStartDate;
    private Date suggestCompleteDate;
    @Builder.Default
    private EnumStatus status = EnumStatus.In_open;
    @Builder.Default
    private EnumPriority priority = EnumPriority.LOW;
    @Builder.Default
    private boolean star = false;
    @Builder.Default
    private int issueNumber = 0;
    private UUID assetId;
    private UUID workOrderId;
    private UUID maintenanceId;
    private UUID userId; // assigned technician
    private Date createdAt;
    private Date updatedAt;

    public static Task create(String name, UUID workOrderId, UUID assetId, UUID maintenanceId) {
        if (name == null || name.isBlank()) throw new ValidationException("Task name cannot be blank");
        return Task.builder()
                .id(UUID.randomUUID())
                .taskName(name)
                .workOrderId(workOrderId)
                .assetId(assetId)
                .maintenanceId(maintenanceId)
                .status(EnumStatus.In_open)
                .priority(EnumPriority.LOW)
                .star(false)
                .issueNumber(0)
                .build();
    }

    public void update(String name, String location, String description,
                       Date startDate, Date completeDate,
                       Date suggestStartDate, Date suggestCompleteDate) {
        if (name != null && !name.isBlank()) this.taskName = name;
        if (location != null) this.taskLocation = location;
        if (description != null) this.taskDescription = description;
        if (startDate != null) this.startDate = startDate;
        if (completeDate != null) this.completeDate = completeDate;
        if (suggestStartDate != null) this.suggestStartDate = suggestStartDate;
        if (suggestCompleteDate != null) this.suggestCompleteDate = suggestCompleteDate;
    }

    public void assign(UUID userId) {
        this.userId = userId;
    }

    public void changeStatus(EnumStatus newStatus) {
        if (this.status == EnumStatus.Closed || this.status == EnumStatus.Cancelled) {
            throw new ValidationException("Cannot change status of a closed or cancelled task");
        }
        this.status = newStatus;
    }

    public void changePriority(EnumPriority newPriority) {
        if (newPriority != null) this.priority = newPriority;
    }

    public void toggleStar() {
        this.star = !this.star;
    }

    public void incrementIssueNumber() { this.issueNumber++; }
}
