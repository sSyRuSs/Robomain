package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.inventory.model.Inventory;
import com.example.Robomain.domain.inventory.repository.IInventoryRepository;
import com.example.Robomain.domain.shared.enums.EnumInventoryStatus;
import com.example.Robomain.infrastructure.persistence.jpa.InventoryJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.InventoryMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InventoryRepositoryImpl implements IInventoryRepository {

    private final InventoryJpaRepository jpaRepository;
    private final InventoryMapper mapper;

    @Override public Optional<Inventory> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override public boolean existsByItemCode(String itemCode) {
        return jpaRepository.existsByItemCode(itemCode);
    }

    @Override
    public List<Inventory> search(String keyword, UUID categoryId, UUID warehouseId,
                                   EnumInventoryStatus status, UUID enterpriseId, int page, int size) {
        return jpaRepository.search(keyword, categoryId, warehouseId, status, enterpriseId,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public long count(String keyword, UUID categoryId, UUID warehouseId,
                      EnumInventoryStatus status, UUID enterpriseId) {
        return jpaRepository.countSearch(keyword, categoryId, warehouseId, status, enterpriseId);
    }

    @Override public Inventory save(Inventory i) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(i)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
