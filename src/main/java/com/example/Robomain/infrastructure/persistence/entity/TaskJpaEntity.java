package com.example.Robomain.infrastructure.persistence.entity;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;
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
@Table(name = "task")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class TaskJpaEntity extends BaseJpaEntity {

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_location")
    private String taskLocation;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "task_start_date")
    private Date startDate;

    @Column(name = "task_end_date")
    private Date completeDate;

    @Column(name = "task_suggest_start_date")
    private Date suggestStartDate;

    @Column(name = "task_suggest_end_date")
    private Date suggestCompleteDate;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    @Column(name = "task_priority")
    @Enumerated(EnumType.STRING)
    private EnumPriority priority;

    @Column(name = "task_star")
    private boolean star;

    @Builder.Default
    @Column(name = "task_issue_number")
    private int issueNumber = 0;

    @Column(name = "asset_id")
    private UUID assetId;

    @Column(name = "work_order_id")
    private UUID workOrderId;

    @Column(name = "maintenance_id")
    private UUID maintenanceId;

    @Column(name = "user_id")
    private UUID userId;
}
