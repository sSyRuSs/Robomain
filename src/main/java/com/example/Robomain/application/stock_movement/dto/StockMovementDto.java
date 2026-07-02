package com.example.Robomain.application.stock_movement.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMovementType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class StockMovementDto {
    private UUID id;
    private String movementCode;
    private UUID inventoryId;
    private EnumMovementType movementType;
    private int quantity;
    private int balanceAfter;
    private UUID fromWarehouseId;
    private UUID toWarehouseId;
    private String reason;
    private UUID workOrderId;
    private UUID taskId;
    private UUID purchaseOrderId;
    private UUID processedBy;
    private Date processedAt;
    private BigDecimal unitCost;
    private BigDecimal totalCost;
}
