package com.example.Robomain.application.stock_movement.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetStockMovementByIdQuery {
    private final UUID movementId;
}
