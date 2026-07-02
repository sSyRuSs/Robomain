package com.example.Robomain.application.inventory.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumInventoryStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class InventoryDto {
    private UUID id;
    private String itemCode;
    private String itemName;
    private String description;
    private UUID categoryId;
    private int quantity;
    private int minStockLevel;
    private int reorderPoint;
    private int reorderQuantity;
    private BigDecimal unitCost;
    private String unit;
    private UUID warehouseId;
    private String binLocation;
    private UUID preferredSupplierId;
    private EnumInventoryStatus status;
    private UUID enterpriseId;
    private boolean lowStock;
    private boolean needsReorder;
}
