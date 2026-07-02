package com.example.Robomain.domain.stock_movement.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMovementType;
import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * StockMovement domain entity — immutable audit record of an inventory quantity change.
 * Created for every stock IN / OUT / TRANSFER / ADJUSTMENT / RETURN event.
 * Never updated after creation.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockMovement {

    private UUID id;
    private String movementCode;
    private UUID inventoryId;
    private EnumMovementType movementType;
    private int quantity;        // amount involved in this movement
    private int balanceAfter;   // inventory quantity after movement
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
    private Date createdAt;
    private Date updatedAt;

    /**
     * Factory — records a stock movement snapshot.
     * totalCost is computed automatically as unitCost × quantity.
     */
    public static StockMovement record(
            String movementCode, UUID inventoryId, EnumMovementType movementType,
            int quantity, int balanceAfter,
            UUID fromWarehouseId, UUID toWarehouseId, String reason,
            UUID workOrderId, UUID taskId, UUID purchaseOrderId,
            UUID processedBy, BigDecimal unitCost) {

        if (inventoryId == null) throw new ValidationException("StockMovement must reference an inventory item");
        if (movementType == null) throw new ValidationException("Movement type is required");
        if (quantity < 0) throw new ValidationException("Movement quantity cannot be negative");

        // Auto-generate code if not provided
        String code = (movementCode != null && !movementCode.isBlank()) ? movementCode
                : "SM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        BigDecimal total = (unitCost != null)
                ? unitCost.multiply(BigDecimal.valueOf(quantity)) : null;

        return StockMovement.builder()
                .id(UUID.randomUUID())
                .movementCode(code).inventoryId(inventoryId).movementType(movementType)
                .quantity(quantity).balanceAfter(balanceAfter)
                .fromWarehouseId(fromWarehouseId).toWarehouseId(toWarehouseId)
                .reason(reason).workOrderId(workOrderId).taskId(taskId)
                .purchaseOrderId(purchaseOrderId).processedBy(processedBy)
                .processedAt(new Date()).unitCost(unitCost).totalCost(total)
                .build();
    }
}
