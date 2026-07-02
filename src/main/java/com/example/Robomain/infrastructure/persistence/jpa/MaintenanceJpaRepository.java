package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.MaintenanceJpaEntity;

@Repository
public interface MaintenanceJpaRepository extends JpaRepository<MaintenanceJpaEntity, UUID> {

    boolean existsByMaintenanceName(String name);

    @Query("SELECT m FROM MaintenanceJpaEntity m WHERE " +
           "(:keyword IS NULL OR LOWER(m.maintenanceName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<MaintenanceJpaEntity> search(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT COUNT(m) FROM MaintenanceJpaEntity m WHERE " +
           "(:keyword IS NULL OR LOWER(m.maintenanceName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    long countSearch(@Param("keyword") String keyword);
}
