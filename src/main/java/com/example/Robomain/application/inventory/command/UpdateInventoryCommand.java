package com.example.Robomain.application.inventory.command;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumInventoryStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateInventoryCommand {
    private UUID inventoryId;
    private String itemName;
    private String description;
    private String unit;
    private BigDecimal unitCost;
    private int minStockLevel;
    private int reorderPoint;
    private int reorderQuantity;
    private String binLocation;
    private UUID preferredSupplierId;
    private EnumInventoryStatus status;
}
