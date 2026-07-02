package com.example.Robomain.domain.preventive_maintenance.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMaintenanceFrequency;
import com.example.Robomain.domain.shared.enums.EnumPreventiveScheduleStatus;
import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PreventiveMaintenanceSchedule — a recurring maintenance schedule tied to an asset.
 * Drives automated WorkOrder generation based on frequency and interval.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreventiveMaintenanceSchedule {

    private UUID id;
    private String scheduleName;
    private String description;
    private UUID assetId;
    private UUID maintenanceId;
    private EnumMaintenanceFrequency frequency;
    private int intervalCount;
    private Date nextDueDate;
    private Date lastGeneratedAt;
    private int estimatedDurationHours;
    private int leadTimeDays;
    @Builder.Default
    private EnumPreventiveScheduleStatus status = EnumPreventiveScheduleStatus.ACTIVE;
    private UUID enterpriseId;
    private Date createdAt;
    private Date updatedAt;

    public static PreventiveMaintenanceSchedule create(String scheduleName, UUID assetId, UUID maintenanceId,
                                                        EnumMaintenanceFrequency frequency, int intervalCount,
                                                        Date nextDueDate, int estimatedDurationHours,
                                                        int leadTimeDays, UUID enterpriseId) {
        if (scheduleName == null || scheduleName.isBlank()) throw new ValidationException("Schedule name cannot be blank");
        if (assetId == null) throw new ValidationException("Asset is required");
        if (maintenanceId == null) throw new ValidationException("Maintenance template is required");
        if (nextDueDate == null) throw new ValidationException("Next due date is required");
        if (enterpriseId == null) throw new ValidationException("Enterprise is required");
        return PreventiveMaintenanceSchedule.builder()
                .id(UUID.randomUUID()).scheduleName(scheduleName).assetId(assetId).maintenanceId(maintenanceId)
                .frequency(frequency).intervalCount(intervalCount).nextDueDate(nextDueDate)
                .estimatedDurationHours(estimatedDurationHours).leadTimeDays(leadTimeDays)
                .status(EnumPreventiveScheduleStatus.ACTIVE).enterpriseId(enterpriseId)
                .build();
    }

    public void update(String scheduleName, String description, EnumMaintenanceFrequency frequency,
                       int intervalCount, Date nextDueDate, int estimatedDurationHours, int leadTimeDays) {
        if (scheduleName != null && !scheduleName.isBlank()) this.scheduleName = scheduleName;
        if (description != null) this.description = description;
        if (frequency != null) this.frequency = frequency;
        if (intervalCount > 0) this.intervalCount = intervalCount;
        if (nextDueDate != null) this.nextDueDate = nextDueDate;
        if (estimatedDurationHours > 0) this.estimatedDurationHours = estimatedDurationHours;
        if (leadTimeDays >= 0) this.leadTimeDays = leadTimeDays;
    }

    /** Records that a WorkOrder was generated. Advances nextDueDate. */
    public void recordGeneration(Date newNextDueDate) {
        this.lastGeneratedAt = new Date();
        if (newNextDueDate != null) this.nextDueDate = newNextDueDate;
    }

    public void activate()   { this.status = EnumPreventiveScheduleStatus.ACTIVE; }
    public void deactivate() { this.status = EnumPreventiveScheduleStatus.INACTIVE; }
}
