package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.asset.model.Asset;
import com.example.Robomain.domain.asset.repository.IAssetRepository;
import com.example.Robomain.infrastructure.persistence.jpa.AssetJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.AssetMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AssetRepositoryImpl implements IAssetRepository {

    private final AssetJpaRepository jpaRepository;
    private final AssetMapper mapper;

    @Override
    public Optional<Asset> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByAssetCode(String code) {
        return jpaRepository.existsByAssetCode(code);
    }

    @Override
    public List<Asset> search(String keyword, UUID facilityId, UUID categoryId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return jpaRepository.search(keyword, facilityId, categoryId, pageable)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public long count(String keyword, UUID facilityId, UUID categoryId) {
        return jpaRepository.countSearch(keyword, facilityId, categoryId);
    }

    @Override
    public Asset save(Asset asset) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(asset)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
