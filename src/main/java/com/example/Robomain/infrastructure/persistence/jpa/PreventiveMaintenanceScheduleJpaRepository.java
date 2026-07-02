package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumPreventiveScheduleStatus;
import com.example.Robomain.infrastructure.persistence.entity.PreventiveMaintenanceScheduleJpaEntity;

@Repository
public interface PreventiveMaintenanceScheduleJpaRepository
        extends JpaRepository<PreventiveMaintenanceScheduleJpaEntity, UUID> {

    @Query("SELECT s FROM PreventiveMaintenanceScheduleJpaEntity s WHERE " +
           "(:keyword IS NULL OR LOWER(s.scheduleName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:assetId IS NULL OR s.assetId = :assetId) AND " +
           "(:maintenanceId IS NULL OR s.maintenanceId = :maintenanceId) AND " +
           "(:status IS NULL OR s.status = :status) AND " +
           "(:enterpriseId IS NULL OR s.enterpriseId = :enterpriseId)")
    List<PreventiveMaintenanceScheduleJpaEntity> search(
            @Param("keyword") String keyword, @Param("assetId") UUID assetId,
            @Param("maintenanceId") UUID maintenanceId, @Param("status") EnumPreventiveScheduleStatus status,
            @Param("enterpriseId") UUID enterpriseId, Pageable pageable);

    @Query("SELECT COUNT(s) FROM PreventiveMaintenanceScheduleJpaEntity s WHERE " +
           "(:keyword IS NULL OR LOWER(s.scheduleName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:enterpriseId IS NULL OR s.enterpriseId = :enterpriseId)")
    long countSearch(@Param("keyword") String keyword, @Param("enterpriseId") UUID enterpriseId);
}
