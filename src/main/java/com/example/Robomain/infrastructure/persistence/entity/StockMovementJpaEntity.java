package com.example.Robomain.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMovementType;
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
@Table(name = "stock_movement")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class StockMovementJpaEntity extends BaseJpaEntity {

    @Column(name = "movement_code", nullable = false, length = 50)
    private String movementCode;

    @Column(name = "inventory_id", nullable = false)
    private UUID inventoryId;

    @Column(name = "movement_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumMovementType movementType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "balance_after", nullable = false)
    private Integer balanceAfter;

    @Column(name = "from_warehouse_id")
    private UUID fromWarehouseId;

    @Column(name = "to_warehouse_id")
    private UUID toWarehouseId;

    @Column(length = 500)
    private String reason;

    @Column(name = "work_order_id")
    private UUID workOrderId;

    @Column(name = "task_id")
    private UUID taskId;

    @Column(name = "purchase_order_id")
    private UUID purchaseOrderId;

    @Column(name = "processed_by")
    private UUID processedBy;

    @Column(name = "processed_at")
    private Date processedAt;

    @Column(name = "unit_cost", precision = 19, scale = 2)
    private BigDecimal unitCost;

    @Column(name = "total_cost", precision = 19, scale = 2)
    private BigDecimal totalCost;
}
