package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.maintenance.model.Maintenance;
import com.example.Robomain.domain.maintenance.repository.IMaintenanceRepository;
import com.example.Robomain.infrastructure.persistence.jpa.MaintenanceJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.MaintenanceMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MaintenanceRepositoryImpl implements IMaintenanceRepository {

    private final MaintenanceJpaRepository jpaRepository;
    private final MaintenanceMapper mapper;

    @Override public Optional<Maintenance> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override public boolean existsByMaintenanceName(String name) {
        return jpaRepository.existsByMaintenanceName(name);
    }

    @Override public List<Maintenance> search(String keyword, int page, int size) {
        return jpaRepository.search(keyword, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long count(String keyword) {
        return jpaRepository.countSearch(keyword);
    }

    @Override public Maintenance save(Maintenance m) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(m)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
