package com.example.Robomain.domain.asset.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.asset.model.Asset;

public interface IAssetRepository {
    Optional<Asset> findById(UUID id);
    boolean existsByAssetCode(String code);
    List<Asset> search(String keyword, UUID facilityId, UUID categoryId, int page, int size);
    long count(String keyword, UUID facilityId, UUID categoryId);
    Asset save(Asset asset);
    void deleteById(UUID id);
}
