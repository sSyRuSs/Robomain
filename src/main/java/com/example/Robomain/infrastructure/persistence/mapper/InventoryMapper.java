package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.inventory.model.Inventory;
import com.example.Robomain.infrastructure.persistence.entity.InventoryJpaEntity;

@Component
public class InventoryMapper {

    public Inventory toDomain(InventoryJpaEntity e) {
        if (e == null) return null;
        return Inventory.builder()
                .id(e.getId()).itemCode(e.getItemCode()).itemName(e.getItemName())
                .description(e.getDescription()).categoryId(e.getCategoryId())
                .quantity(e.getQuantity() != null ? e.getQuantity() : 0)
                .minStockLevel(e.getMinStockLevel() != null ? e.getMinStockLevel() : 0)
                .reorderPoint(e.getReorderPoint() != null ? e.getReorderPoint() : 0)
                .reorderQuantity(e.getReorderQuantity() != null ? e.getReorderQuantity() : 0)
                .unitCost(e.getUnitCost()).unit(e.getUnit())
                .warehouseId(e.getWarehouseId()).binLocation(e.getBinLocation())
                .preferredSupplierId(e.getPreferredSupplierId()).status(e.getStatus())
                .enterpriseId(e.getEnterpriseId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public InventoryJpaEntity toJpa(Inventory d) {
        if (d == null) return null;
        InventoryJpaEntity e = InventoryJpaEntity.builder()
                .itemCode(d.getItemCode()).itemName(d.getItemName()).description(d.getDescription())
                .categoryId(d.getCategoryId()).quantity(d.getQuantity())
                .minStockLevel(d.getMinStockLevel()).reorderPoint(d.getReorderPoint())
                .reorderQuantity(d.getReorderQuantity()).unitCost(d.getUnitCost()).unit(d.getUnit())
                .warehouseId(d.getWarehouseId()).binLocation(d.getBinLocation())
                .preferredSupplierId(d.getPreferredSupplierId()).status(d.getStatus())
                .enterpriseId(d.getEnterpriseId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
