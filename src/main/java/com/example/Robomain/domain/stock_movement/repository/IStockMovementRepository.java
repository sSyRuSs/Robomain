package com.example.Robomain.domain.stock_movement.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumMovementType;
import com.example.Robomain.domain.stock_movement.model.StockMovement;

public interface IStockMovementRepository {
    Optional<StockMovement> findById(UUID id);
    List<StockMovement> findByInventoryId(UUID inventoryId, EnumMovementType type, int page, int size);
    long countByInventoryId(UUID inventoryId, EnumMovementType type);
    StockMovement save(StockMovement movement);
}
