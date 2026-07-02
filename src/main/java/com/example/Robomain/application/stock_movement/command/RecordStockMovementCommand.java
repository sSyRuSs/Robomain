package com.example.Robomain.application.stock_movement.command;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMovementType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RecordStockMovementCommand {
    private String movementCode; // optional — auto-generated if null

    @NotNull(message = "Inventory ID is required")
    private UUID inventoryId;

    @NotNull(message = "Movement type is required")
    private EnumMovementType movementType;

    /**
     * For IN/OUT/TRANSFER/RETURN: the quantity moved (must be positive).
     * For ADJUSTMENT: the new absolute target quantity.
     */
    @Positive(message = "Quantity must be positive")
    private int quantity;

    private UUID fromWarehouseId;
    private UUID toWarehouseId;
    private String reason;
    private UUID workOrderId;
    private UUID taskId;
    private UUID purchaseOrderId;
    private UUID processedBy;
}
