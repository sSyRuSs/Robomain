package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumSparePartReservationStatus;
import com.example.Robomain.infrastructure.persistence.entity.SparePartReservationJpaEntity;

@Repository
public interface SparePartReservationJpaRepository extends JpaRepository<SparePartReservationJpaEntity, UUID> {

    @Query("SELECT r FROM SparePartReservationJpaEntity r WHERE " +
           "(:inventoryId IS NULL OR r.inventoryId = :inventoryId) AND " +
           "(:workOrderId IS NULL OR r.workOrderId = :workOrderId) AND " +
           "(:status IS NULL OR r.status = :status) AND " +
           "(:enterpriseId IS NULL OR r.enterpriseId = :enterpriseId) " +
           "ORDER BY r.reservedAt DESC")
    List<SparePartReservationJpaEntity> search(
            @Param("inventoryId") UUID inventoryId, @Param("workOrderId") UUID workOrderId,
            @Param("status") EnumSparePartReservationStatus status,
            @Param("enterpriseId") UUID enterpriseId, Pageable pageable);

    @Query("SELECT COUNT(r) FROM SparePartReservationJpaEntity r WHERE " +
           "(:inventoryId IS NULL OR r.inventoryId = :inventoryId) AND " +
           "(:workOrderId IS NULL OR r.workOrderId = :workOrderId) AND " +
           "(:status IS NULL OR r.status = :status) AND " +
           "(:enterpriseId IS NULL OR r.enterpriseId = :enterpriseId)")
    long countSearch(@Param("inventoryId") UUID inventoryId, @Param("workOrderId") UUID workOrderId,
                     @Param("status") EnumSparePartReservationStatus status,
                     @Param("enterpriseId") UUID enterpriseId);
}
