package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.AssetStatusJpaEntity;

@Repository
public interface AssetStatusJpaRepository extends JpaRepository<AssetStatusJpaEntity, UUID> {
    Optional<AssetStatusJpaEntity> findByAssetId(UUID assetId);
}
