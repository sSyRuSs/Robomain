package com.example.Robomain.domain.warehouse.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumWarehouseType;
import com.example.Robomain.domain.warehouse.model.Warehouse;

public interface IWarehouseRepository {
    Optional<Warehouse> findById(UUID id);
    boolean existsByWarehouseCode(String code);
    List<Warehouse> search(String keyword, UUID enterpriseId, EnumWarehouseType type, int page, int size);
    long count(String keyword, UUID enterpriseId);
    Warehouse save(Warehouse warehouse);
    void deleteById(UUID id);
}
