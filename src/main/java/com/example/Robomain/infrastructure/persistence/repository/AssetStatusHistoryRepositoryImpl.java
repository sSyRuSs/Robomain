package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.asset_status_history.model.AssetStatusHistory;
import com.example.Robomain.domain.asset_status_history.repository.IAssetStatusHistoryRepository;
import com.example.Robomain.infrastructure.persistence.jpa.AssetStatusHistoryJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.AssetStatusHistoryMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AssetStatusHistoryRepositoryImpl implements IAssetStatusHistoryRepository {

    private final AssetStatusHistoryJpaRepository jpaRepository;
    private final AssetStatusHistoryMapper mapper;

    @Override
    public List<AssetStatusHistory> findByAssetId(UUID assetId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "changedAt"));
        return jpaRepository.findByAssetIdOrderByChangedAtDesc(assetId, pageable)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public long countByAssetId(UUID assetId) {
        return jpaRepository.countByAssetId(assetId);
    }

    @Override
    public AssetStatusHistory save(AssetStatusHistory history) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(history)));
    }
}
