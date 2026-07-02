package com.example.Robomain.infrastructure.persistence.entity;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumSparePartReservationStatus;
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
@Table(name = "spare_part_reservation")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class SparePartReservationJpaEntity extends BaseJpaEntity {

    @Column(name = "inventory_id", nullable = false)
    private UUID inventoryId;

    @Column(name = "work_order_id", nullable = false)
    private UUID workOrderId;

    @Column(name = "task_id")
    private UUID taskId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumSparePartReservationStatus status;

    @Column(name = "reserved_at", nullable = false)
    private Date reservedAt;

    @Column(name = "issued_at")
    private Date issuedAt;

    @Column(name = "cancelled_at")
    private Date cancelledAt;

    @Column(length = 500)
    private String note;

    @Column(name = "reserved_by")
    private UUID reservedBy;

    @Column(name = "enterprise_id", nullable = false)
    private UUID enterpriseId;
}
