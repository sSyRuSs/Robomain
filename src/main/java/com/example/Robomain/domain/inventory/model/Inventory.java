package com.example.Robomain.domain.inventory.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumInventoryStatus;
import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Inventory domain entity — a spare part or consumable tracked in a warehouse.
 * Quantity is managed only through StockMovement commands to ensure audit trail.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    private UUID id;
    private String itemCode;
    private String itemName;
    private String description;
    private UUID categoryId;
    @Builder.Default
    private int quantity = 0;
    @Builder.Default
    private int minStockLevel = 0;
    @Builder.Default
    private int reorderPoint = 0;
    @Builder.Default
    private int reorderQuantity = 0;
    private BigDecimal unitCost;
    private String unit;
    private UUID warehouseId;
    private String binLocation;
    private UUID preferredSupplierId;
    @Builder.Default
    private EnumInventoryStatus status = EnumInventoryStatus.AVAILABLE;
    private UUID enterpriseId;
    private Date createdAt;
    private Date updatedAt;

    public static Inventory create(String itemCode, String itemName,
                                    UUID categoryId, UUID warehouseId, UUID enterpriseId) {
        if (itemCode == null || itemCode.isBlank()) throw new ValidationException("Item code cannot be blank");
        if (itemName == null || itemName.isBlank()) throw new ValidationException("Item name cannot be blank");
        if (enterpriseId == null) throw new ValidationException("Inventory must belong to an enterprise");
        return Inventory.builder()
                .id(UUID.randomUUID())
                .itemCode(itemCode).itemName(itemName)
                .categoryId(categoryId).warehouseId(warehouseId)
                .enterpriseId(enterpriseId)
                .quantity(0)
                .status(EnumInventoryStatus.AVAILABLE)
                .build();
    }

    public void update(String itemName, String description, String unit, BigDecimal unitCost,
                       int minStockLevel, int reorderPoint, int reorderQuantity,
                       String binLocation, UUID preferredSupplierId) {
        if (itemName != null && !itemName.isBlank()) this.itemName = itemName;
        if (description != null) this.description = description;
        if (unit != null) this.unit = unit;
        if (unitCost != null) this.unitCost = unitCost;
        if (minStockLevel >= 0) this.minStockLevel = minStockLevel;
        if (reorderPoint >= 0) this.reorderPoint = reorderPoint;
        if (reorderQuantity >= 0) this.reorderQuantity = reorderQuantity;
        if (binLocation != null) this.binLocation = binLocation;
        if (preferredSupplierId != null) this.preferredSupplierId = preferredSupplierId;
    }

    /** Increase stock — called on IN / RETURN movements. */
    public void receiveStock(int qty) {
        if (qty <= 0) throw new ValidationException("Receive quantity must be positive");
        this.quantity += qty;
    }

    /** Decrease stock — called on OUT / TRANSFER movements. Throws if insufficient. */
    public void issueStock(int qty) {
        if (qty <= 0) throw new ValidationException("Issue quantity must be positive");
        if (this.quantity < qty) {
            throw new ValidationException(
                    "Insufficient stock: available=" + this.quantity + ", requested=" + qty);
        }
        this.quantity -= qty;
    }

    /** Set an exact quantity — called on ADJUSTMENT movements. */
    public void adjustQuantity(int newQty) {
        if (newQty < 0) throw new ValidationException("Quantity cannot be negative");
        this.quantity = newQty;
    }

    public void changeStatus(EnumInventoryStatus status) { this.status = status; }

    /** True when stock is at or below the minimum threshold. */
    public boolean isLowStock() { return this.quantity <= this.minStockLevel; }

    /** True when a reorder should be triggered. */
    public boolean needsReorder() { return this.quantity <= this.reorderPoint; }
}
