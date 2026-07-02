package com.example.Robomain.domain.work_order.model;

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
 * WorkOrder domain entity — represents a planned or reactive maintenance job.
 * Belongs to a Maintenance program and is linked to an Asset.
 * Tracks task count to determine completion.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrder {

    private UUID id;
    private String workOrderName;
    private String workOrderLocation;
    private String workOrderDescription;
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
    private int taskTotal = 0;
    private UUID assetId;
    private UUID maintenanceId;
    private Date createdAt;
    private Date updatedAt;

    public static WorkOrder create(String name, UUID assetId, UUID maintenanceId) {
        if (name == null || name.isBlank()) throw new ValidationException("Work order name cannot be blank");
        return WorkOrder.builder()
                .id(UUID.randomUUID())
                .workOrderName(name)
                .assetId(assetId)
                .maintenanceId(maintenanceId)
                .status(EnumStatus.In_open)
                .priority(EnumPriority.LOW)
                .star(false)
                .taskTotal(0)
                .build();
    }

    public void update(String name, String location, String description,
                       Date startDate, Date completeDate,
                       Date suggestStartDate, Date suggestCompleteDate) {
        if (name != null && !name.isBlank()) this.workOrderName = name;
        if (location != null) this.workOrderLocation = location;
        if (description != null) this.workOrderDescription = description;
        if (startDate != null) this.startDate = startDate;
        if (completeDate != null) this.completeDate = completeDate;
        if (suggestStartDate != null) this.suggestStartDate = suggestStartDate;
        if (suggestCompleteDate != null) this.suggestCompleteDate = suggestCompleteDate;
    }

    public void changeStatus(EnumStatus newStatus) {
        if (this.status == EnumStatus.Closed || this.status == EnumStatus.Cancelled) {
            throw new ValidationException(
                    "Cannot change status of a closed or cancelled work order");
        }
        this.status = newStatus;
    }

    public void changePriority(EnumPriority newPriority) {
        if (newPriority != null) this.priority = newPriority;
    }

    public void toggleStar() {
        this.star = !this.star;
    }

    public void incrementTaskTotal() { this.taskTotal++; }

    public void decrementTaskTotal() {
        if (this.taskTotal > 0) this.taskTotal--;
    }
}
