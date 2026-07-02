package com.example.Robomain.application.inventory.command;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumInventoryStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateInventoryCommand {
    @NotBlank(message = "Item code is required")
    private String itemCode;
    @NotBlank(message = "Item name is required")
    private String itemName;
    private String description;
    private UUID categoryId;
    private int minStockLevel;
    private int reorderPoint;
    private int reorderQuantity;
    private BigDecimal unitCost;
    private String unit;
    private UUID warehouseId;
    private String binLocation;
    private UUID preferredSupplierId;
    @NotNull(message = "Enterprise ID is required")
    private UUID enterpriseId;
}
