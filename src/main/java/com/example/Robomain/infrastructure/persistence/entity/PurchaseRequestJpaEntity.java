package com.example.Robomain.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPRStatus;
import com.example.Robomain.domain.shared.enums.EnumPRType;
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
@Table(name = "purchase_request")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class PurchaseRequestJpaEntity extends BaseJpaEntity {

    @Column(nullable = false)
    private String requestCode;

    @Column(name = "inventory_id", nullable = false)
    private UUID inventoryId;

    @Column(nullable = false)
    private int requestedQuantity;

    @Column(name = "estimated_cost", precision = 19, scale = 2)
    private BigDecimal estimatedCost;

    @Column(name = "req_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumPRStatus status;

    @Column(name = "request_type")
    @Enumerated(EnumType.STRING)
    private EnumPRType type;

    @Column(length = 500)
    private String reason;

    @Column(name = "requested_by_id")
    private UUID requestedBy;

    @Column(name = "requested_date", nullable = false)
    private LocalDate requestedDate;

    @Column(name = "required_date")
    private LocalDate requiredDate;

    @Column(name = "approved_by_id")
    private UUID approvedBy;

    @Column(name = "approved_date")
    private LocalDate approvedDate;

    @Column(name = "approval_notes", length = 500)
    private String approvalNotes;

    @Column(name = "vendor_id")
    private UUID vendorId;

    @Column(name = "enterprise_id", nullable = false)
    private UUID enterpriseId;
}
