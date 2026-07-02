package com.example.Robomain.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.asset_status.model.AssetStatus;
import com.example.Robomain.domain.asset_status.repository.IAssetStatusRepository;
import com.example.Robomain.infrastructure.persistence.jpa.AssetStatusJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.AssetStatusMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AssetStatusRepositoryImpl implements IAssetStatusRepository {

    private final AssetStatusJpaRepository jpaRepository;
    private final AssetStatusMapper mapper;

    @Override
    public Optional<AssetStatus> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<AssetStatus> findByAssetId(UUID assetId) {
        return jpaRepository.findByAssetId(assetId).map(mapper::toDomain);
    }

    @Override
    public AssetStatus save(AssetStatus status) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(status)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
