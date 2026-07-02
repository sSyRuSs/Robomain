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
@Table(name = "work_order")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class WorkOrderJpaEntity extends BaseJpaEntity {

    @Column(name = "work_order_name")
    private String workOrderName;

    @Column(name = "work_order_location")
    private String workOrderLocation;

    @Column(name = "work_order_description")
    private String workOrderDescription;

    @Column(name = "work_order_start_date")
    private Date startDate;

    @Column(name = "work_order_end_date")
    private Date completeDate;

    @Column(name = "work_order_suggest_start_date")
    private Date suggestStartDate;

    @Column(name = "work_order_suggest_end_date")
    private Date suggestCompleteDate;

    @Column(name = "work_order_status")
    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    @Column(name = "work_order_priority")
    @Enumerated(EnumType.STRING)
    private EnumPriority priority;

    @Column(name = "work_order_star")
    private boolean star;

    @Builder.Default
    @Column(name = "task_total")
    private int taskTotal = 0;

    @Column(name = "asset_id")
    private UUID assetId;

    @Column(name = "maintenance_id")
    private UUID maintenanceId;
}
