package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumMovementType;
import com.example.Robomain.infrastructure.persistence.entity.StockMovementJpaEntity;

@Repository
public interface StockMovementJpaRepository extends JpaRepository<StockMovementJpaEntity, UUID> {

    @Query("SELECT m FROM StockMovementJpaEntity m WHERE " +
           "m.inventoryId = :inventoryId AND " +
           "(:type IS NULL OR m.movementType = :type) " +
           "ORDER BY m.processedAt DESC")
    List<StockMovementJpaEntity> findByInventoryId(
            @Param("inventoryId") UUID inventoryId,
            @Param("type") EnumMovementType type,
            Pageable pageable);

    @Query("SELECT COUNT(m) FROM StockMovementJpaEntity m WHERE " +
           "m.inventoryId = :inventoryId AND " +
           "(:type IS NULL OR m.movementType = :type)")
    long countByInventoryId(
            @Param("inventoryId") UUID inventoryId,
            @Param("type") EnumMovementType type);
}
