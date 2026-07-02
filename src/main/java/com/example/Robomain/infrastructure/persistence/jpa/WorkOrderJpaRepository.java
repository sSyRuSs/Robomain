package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;
import com.example.Robomain.infrastructure.persistence.entity.WorkOrderJpaEntity;

@Repository
public interface WorkOrderJpaRepository extends JpaRepository<WorkOrderJpaEntity, UUID> {

    @Query("SELECT w FROM WorkOrderJpaEntity w WHERE " +
           "(:keyword IS NULL OR LOWER(w.workOrderName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:status IS NULL OR w.status = :status) AND " +
           "(:priority IS NULL OR w.priority = :priority) AND " +
           "(:maintenanceId IS NULL OR w.maintenanceId = :maintenanceId) AND " +
           "(:assetId IS NULL OR w.assetId = :assetId)")
    List<WorkOrderJpaEntity> search(
            @Param("keyword") String keyword,
            @Param("status") EnumStatus status,
            @Param("priority") EnumPriority priority,
            @Param("maintenanceId") UUID maintenanceId,
            @Param("assetId") UUID assetId,
            Pageable pageable);

    @Query("SELECT COUNT(w) FROM WorkOrderJpaEntity w WHERE " +
           "(:keyword IS NULL OR LOWER(w.workOrderName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:status IS NULL OR w.status = :status) AND " +
           "(:priority IS NULL OR w.priority = :priority) AND " +
           "(:maintenanceId IS NULL OR w.maintenanceId = :maintenanceId) AND " +
           "(:assetId IS NULL OR w.assetId = :assetId)")
    long countSearch(
            @Param("keyword") String keyword,
            @Param("status") EnumStatus status,
            @Param("priority") EnumPriority priority,
            @Param("maintenanceId") UUID maintenanceId,
            @Param("assetId") UUID assetId);

    @Modifying
    @Query("UPDATE WorkOrderJpaEntity w SET w.taskTotal = w.taskTotal + 1 WHERE w.id = :id")
    void incrementTaskTotal(@Param("id") UUID id);
}
