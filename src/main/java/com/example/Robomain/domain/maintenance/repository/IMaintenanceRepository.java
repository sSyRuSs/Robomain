package com.example.Robomain.domain.maintenance.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.maintenance.model.Maintenance;

public interface IMaintenanceRepository {
    Optional<Maintenance> findById(UUID id);
    boolean existsByMaintenanceName(String name);
    List<Maintenance> search(String keyword, int page, int size);
    long count(String keyword);
    Maintenance save(Maintenance maintenance);
    void deleteById(UUID id);
}
