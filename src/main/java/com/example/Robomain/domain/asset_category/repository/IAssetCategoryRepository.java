package com.example.Robomain.domain.asset_category.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.asset_category.model.AssetCategory;

public interface IAssetCategoryRepository {
    Optional<AssetCategory> findById(UUID id);
    boolean existsByAssetCatName(String name);
    List<AssetCategory> search(String keyword, UUID parentId, int page, int size);
    long count(String keyword);
    AssetCategory save(AssetCategory category);
    void deleteById(UUID id);
}
