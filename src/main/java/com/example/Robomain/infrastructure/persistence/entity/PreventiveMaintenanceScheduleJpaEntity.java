package com.example.Robomain.infrastructure.persistence.entity;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMaintenanceFrequency;
import com.example.Robomain.domain.shared.enums.EnumPreventiveScheduleStatus;
import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "preventive_maintenance_schedule")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class PreventiveMaintenanceScheduleJpaEntity extends BaseJpaEntity {

    @Column(name = "schedule_name", nullable = false)
    private String scheduleName;

    @Column(length = 1000)
    private String description;

    @Column(name = "asset_id", nullable = false)
    private UUID assetId;

    @Column(name = "maintenance_id", nullable = false)
    private UUID maintenanceId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumMaintenanceFrequency frequency;

    @Column(name = "interval_count", nullable = false)
    private int intervalCount;

    @Column(name = "next_due_date", nullable = false)
    private Date nextDueDate;

    @Column(name = "last_generated_at")
    private Date lastGeneratedAt;

    @Column(name = "estimated_duration_hours", nullable = false)
    private int estimatedDurationHours;

    @Column(name = "lead_time_days", nullable = false)
    private int leadTimeDays;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumPreventiveScheduleStatus status;

    @Column(name = "enterprise_id", nullable = false)
    private UUID enterpriseId;
}
