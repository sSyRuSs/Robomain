package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumInventoryStatus;
import com.example.Robomain.infrastructure.persistence.entity.InventoryJpaEntity;

@Repository
public interface InventoryJpaRepository extends JpaRepository<InventoryJpaEntity, UUID> {

    boolean existsByItemCode(String itemCode);

    @Query("SELECT i FROM InventoryJpaEntity i WHERE " +
           "(:keyword IS NULL OR LOWER(i.itemName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "   OR LOWER(i.itemCode) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:categoryId IS NULL OR i.categoryId = :categoryId) AND " +
           "(:warehouseId IS NULL OR i.warehouseId = :warehouseId) AND " +
           "(:status IS NULL OR i.status = :status) AND " +
           "(:enterpriseId IS NULL OR i.enterpriseId = :enterpriseId)")
    List<InventoryJpaEntity> search(
            @Param("keyword") String keyword,
            @Param("categoryId") UUID categoryId,
            @Param("warehouseId") UUID warehouseId,
            @Param("status") EnumInventoryStatus status,
            @Param("enterpriseId") UUID enterpriseId,
            Pageable pageable);

    @Query("SELECT COUNT(i) FROM InventoryJpaEntity i WHERE " +
           "(:keyword IS NULL OR LOWER(i.itemName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "   OR LOWER(i.itemCode) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:categoryId IS NULL OR i.categoryId = :categoryId) AND " +
           "(:warehouseId IS NULL OR i.warehouseId = :warehouseId) AND " +
           "(:status IS NULL OR i.status = :status) AND " +
           "(:enterpriseId IS NULL OR i.enterpriseId = :enterpriseId)")
    long countSearch(
            @Param("keyword") String keyword,
            @Param("categoryId") UUID categoryId,
            @Param("warehouseId") UUID warehouseId,
            @Param("status") EnumInventoryStatus status,
            @Param("enterpriseId") UUID enterpriseId);
}
