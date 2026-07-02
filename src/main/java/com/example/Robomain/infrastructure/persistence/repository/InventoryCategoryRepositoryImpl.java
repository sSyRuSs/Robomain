package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.inventory_category.model.InventoryCategory;
import com.example.Robomain.domain.inventory_category.repository.IInventoryCategoryRepository;
import com.example.Robomain.infrastructure.persistence.jpa.InventoryCategoryJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.InventoryCategoryMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InventoryCategoryRepositoryImpl implements IInventoryCategoryRepository {

    private final InventoryCategoryJpaRepository jpaRepository;
    private final InventoryCategoryMapper mapper;

    @Override public Optional<InventoryCategory> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override public boolean existsByCategoryCode(String code) {
        return jpaRepository.existsByCategoryCode(code);
    }

    @Override
    public List<InventoryCategory> search(String keyword, UUID enterpriseId, UUID parentId, int page, int size) {
        return jpaRepository.search(keyword, enterpriseId, parentId,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long count(String keyword, UUID enterpriseId) {
        return jpaRepository.countSearch(keyword, enterpriseId);
    }

    @Override public InventoryCategory save(InventoryCategory c) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(c)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
