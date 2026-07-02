package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.asset_category.model.AssetCategory;
import com.example.Robomain.domain.asset_category.repository.IAssetCategoryRepository;
import com.example.Robomain.infrastructure.persistence.jpa.AssetCategoryJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.AssetCategoryMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AssetCategoryRepositoryImpl implements IAssetCategoryRepository {

    private final AssetCategoryJpaRepository jpaRepository;
    private final AssetCategoryMapper mapper;

    @Override
    public Optional<AssetCategory> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByAssetCatName(String name) {
        return jpaRepository.existsByAssetCatName(name);
    }

    @Override
    public List<AssetCategory> search(String keyword, UUID parentId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return jpaRepository.search(keyword, parentId, pageable).stream().map(mapper::toDomain).toList();
    }

    @Override
    public long count(String keyword) {
        return jpaRepository.countSearch(keyword);
    }

    @Override
    public AssetCategory save(AssetCategory category) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(category)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
