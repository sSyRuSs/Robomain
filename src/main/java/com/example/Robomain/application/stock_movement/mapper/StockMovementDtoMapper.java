package com.example.Robomain.application.stock_movement.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.stock_movement.dto.StockMovementDto;
import com.example.Robomain.domain.stock_movement.model.StockMovement;

@Component
public class StockMovementDtoMapper {

    public StockMovementDto toDto(StockMovement m) {
        return StockMovementDto.builder()
                .id(m.getId()).movementCode(m.getMovementCode()).inventoryId(m.getInventoryId())
                .movementType(m.getMovementType()).quantity(m.getQuantity())
                .balanceAfter(m.getBalanceAfter())
                .fromWarehouseId(m.getFromWarehouseId()).toWarehouseId(m.getToWarehouseId())
                .reason(m.getReason()).workOrderId(m.getWorkOrderId()).taskId(m.getTaskId())
                .purchaseOrderId(m.getPurchaseOrderId()).processedBy(m.getProcessedBy())
                .processedAt(m.getProcessedAt()).unitCost(m.getUnitCost()).totalCost(m.getTotalCost())
                .build();
    }
}
