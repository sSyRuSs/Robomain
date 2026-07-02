package com.example.Robomain.domain.inventory.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.inventory.model.Inventory;
import com.example.Robomain.domain.shared.enums.EnumInventoryStatus;

public interface IInventoryRepository {
    Optional<Inventory> findById(UUID id);
    boolean existsByItemCode(String itemCode);
    List<Inventory> search(String keyword, UUID categoryId, UUID warehouseId,
                           EnumInventoryStatus status, UUID enterpriseId, int page, int size);
    long count(String keyword, UUID categoryId, UUID warehouseId,
               EnumInventoryStatus status, UUID enterpriseId);
    Inventory save(Inventory inventory);
    void deleteById(UUID id);
}
