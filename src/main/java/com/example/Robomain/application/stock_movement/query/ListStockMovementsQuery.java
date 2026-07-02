package com.example.Robomain.application.stock_movement.query;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMovementType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListStockMovementsQuery {
    private UUID inventoryId;
    private EnumMovementType type;
    private int page = 0;
    private int size = 10;
}
