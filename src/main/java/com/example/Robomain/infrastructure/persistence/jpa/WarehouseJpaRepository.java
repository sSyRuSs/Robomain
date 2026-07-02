package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumWarehouseType;
import com.example.Robomain.infrastructure.persistence.entity.WarehouseJpaEntity;

@Repository
public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpaEntity, UUID> {

    boolean existsByWarehouseCode(String code);

    @Query("SELECT w FROM WarehouseJpaEntity w WHERE " +
           "(:keyword IS NULL OR LOWER(w.warehouseName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:enterpriseId IS NULL OR w.enterpriseId = :enterpriseId) AND " +
           "(:type IS NULL OR w.type = :type)")
    List<WarehouseJpaEntity> search(
            @Param("keyword") String keyword,
            @Param("enterpriseId") UUID enterpriseId,
            @Param("type") EnumWarehouseType type,
            Pageable pageable);

    @Query("SELECT COUNT(w) FROM WarehouseJpaEntity w WHERE " +
           "(:keyword IS NULL OR LOWER(w.warehouseName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:enterpriseId IS NULL OR w.enterpriseId = :enterpriseId)")
    long countSearch(@Param("keyword") String keyword, @Param("enterpriseId") UUID enterpriseId);
}
