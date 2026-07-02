package com.example.Robomain.domain.asset_status.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.asset_status.model.AssetStatus;

public interface IAssetStatusRepository {
    Optional<AssetStatus> findById(UUID id);
    Optional<AssetStatus> findByAssetId(UUID assetId);
    AssetStatus save(AssetStatus status);
    void deleteById(UUID id);
}
