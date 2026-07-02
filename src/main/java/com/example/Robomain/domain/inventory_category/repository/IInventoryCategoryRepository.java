package com.example.Robomain.domain.inventory_category.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.inventory_category.model.InventoryCategory;

public interface IInventoryCategoryRepository {
    Optional<InventoryCategory> findById(UUID id);
    boolean existsByCategoryCode(String code);
    List<InventoryCategory> search(String keyword, UUID enterpriseId, UUID parentId, int page, int size);
    long count(String keyword, UUID enterpriseId);
    InventoryCategory save(InventoryCategory category);
    void deleteById(UUID id);
}
