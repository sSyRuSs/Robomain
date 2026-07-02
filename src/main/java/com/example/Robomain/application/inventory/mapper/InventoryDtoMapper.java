package com.example.Robomain.application.inventory.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.inventory.dto.InventoryDto;
import com.example.Robomain.domain.inventory.model.Inventory;

@Component
public class InventoryDtoMapper {

    public InventoryDto toDto(Inventory i) {
        return InventoryDto.builder()
                .id(i.getId()).itemCode(i.getItemCode()).itemName(i.getItemName())
                .description(i.getDescription()).categoryId(i.getCategoryId())
                .quantity(i.getQuantity()).minStockLevel(i.getMinStockLevel())
                .reorderPoint(i.getReorderPoint()).reorderQuantity(i.getReorderQuantity())
                .unitCost(i.getUnitCost()).unit(i.getUnit())
                .warehouseId(i.getWarehouseId()).binLocation(i.getBinLocation())
                .preferredSupplierId(i.getPreferredSupplierId()).status(i.getStatus())
                .enterpriseId(i.getEnterpriseId())
                .lowStock(i.isLowStock()).needsReorder(i.needsReorder())
                .build();
    }
}
