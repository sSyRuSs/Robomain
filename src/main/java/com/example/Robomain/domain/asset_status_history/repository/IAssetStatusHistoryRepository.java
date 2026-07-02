package com.example.Robomain.domain.asset_status_history.repository;

import java.util.List;
import java.util.UUID;

import com.example.Robomain.domain.asset_status_history.model.AssetStatusHistory;

public interface IAssetStatusHistoryRepository {
    List<AssetStatusHistory> findByAssetId(UUID assetId, int page, int size);
    long countByAssetId(UUID assetId);
    AssetStatusHistory save(AssetStatusHistory history);
}
