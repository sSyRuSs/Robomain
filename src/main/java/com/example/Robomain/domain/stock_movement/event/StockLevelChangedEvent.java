package com.example.Robomain.domain.stock_movement.event;

import java.util.UUID;

/**
 * Published after a stock movement changes the inventory quantity.
 * Downstream handlers can trigger reorder notifications or purchase requests.
 */
public record StockLevelChangedEvent(
        UUID inventoryId, String itemCode,
        int newQuantity, int reorderPoint,
        boolean needsReorder) {}
