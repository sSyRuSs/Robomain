package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumWarehouseType;
import com.example.Robomain.domain.warehouse.model.Warehouse;
import com.example.Robomain.domain.warehouse.repository.IWarehouseRepository;
import com.example.Robomain.infrastructure.persistence.jpa.WarehouseJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.WarehouseMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WarehouseRepositoryImpl implements IWarehouseRepository {

    private final WarehouseJpaRepository jpaRepository;
    private final WarehouseMapper mapper;

    @Override public Optional<Warehouse> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override public boolean existsByWarehouseCode(String code) {
        return jpaRepository.existsByWarehouseCode(code);
    }

    @Override
    public List<Warehouse> search(String keyword, UUID enterpriseId, EnumWarehouseType type, int page, int size) {
        return jpaRepository.search(keyword, enterpriseId, type,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long count(String keyword, UUID enterpriseId) {
        return jpaRepository.countSearch(keyword, enterpriseId);
    }

    @Override public Warehouse save(Warehouse w) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(w)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
