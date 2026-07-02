package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.AssetStatusHistoryJpaEntity;

@Repository
public interface AssetStatusHistoryJpaRepository extends JpaRepository<AssetStatusHistoryJpaEntity, UUID> {

    List<AssetStatusHistoryJpaEntity> findByAssetIdOrderByChangedAtDesc(UUID assetId, Pageable pageable);

    @Query("SELECT COUNT(h) FROM AssetStatusHistoryJpaEntity h WHERE h.assetId = :assetId")
    long countByAssetId(@Param("assetId") UUID assetId);
}
