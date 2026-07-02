package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumMovementType;
import com.example.Robomain.domain.stock_movement.model.StockMovement;
import com.example.Robomain.domain.stock_movement.repository.IStockMovementRepository;
import com.example.Robomain.infrastructure.persistence.jpa.StockMovementJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.StockMovementMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StockMovementRepositoryImpl implements IStockMovementRepository {

    private final StockMovementJpaRepository jpaRepository;
    private final StockMovementMapper mapper;

    @Override public Optional<StockMovement> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<StockMovement> findByInventoryId(UUID inventoryId, EnumMovementType type, int page, int size) {
        return jpaRepository.findByInventoryId(inventoryId, type, PageRequest.of(page, size))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long countByInventoryId(UUID inventoryId, EnumMovementType type) {
        return jpaRepository.countByInventoryId(inventoryId, type);
    }

    @Override public StockMovement save(StockMovement m) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(m)));
    }
}
