package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.stock_movement.model.StockMovement;
import com.example.Robomain.infrastructure.persistence.entity.StockMovementJpaEntity;

@Component
public class StockMovementMapper {

    public StockMovement toDomain(StockMovementJpaEntity e) {
        if (e == null) return null;
        return StockMovement.builder()
                .id(e.getId()).movementCode(e.getMovementCode()).inventoryId(e.getInventoryId())
                .movementType(e.getMovementType()).quantity(e.getQuantity()).balanceAfter(e.getBalanceAfter())
                .fromWarehouseId(e.getFromWarehouseId()).toWarehouseId(e.getToWarehouseId())
                .reason(e.getReason()).workOrderId(e.getWorkOrderId()).taskId(e.getTaskId())
                .purchaseOrderId(e.getPurchaseOrderId()).processedBy(e.getProcessedBy())
                .processedAt(e.getProcessedAt()).unitCost(e.getUnitCost()).totalCost(e.getTotalCost())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public StockMovementJpaEntity toJpa(StockMovement d) {
        if (d == null) return null;
        StockMovementJpaEntity e = StockMovementJpaEntity.builder()
                .movementCode(d.getMovementCode()).inventoryId(d.getInventoryId())
                .movementType(d.getMovementType()).quantity(d.getQuantity()).balanceAfter(d.getBalanceAfter())
                .fromWarehouseId(d.getFromWarehouseId()).toWarehouseId(d.getToWarehouseId())
                .reason(d.getReason()).workOrderId(d.getWorkOrderId()).taskId(d.getTaskId())
                .purchaseOrderId(d.getPurchaseOrderId()).processedBy(d.getProcessedBy())
                .processedAt(d.getProcessedAt()).unitCost(d.getUnitCost()).totalCost(d.getTotalCost())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
