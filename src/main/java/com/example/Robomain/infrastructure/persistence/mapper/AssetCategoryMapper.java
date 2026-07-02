package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.asset_category.model.AssetCategory;
import com.example.Robomain.infrastructure.persistence.entity.AssetCategoryJpaEntity;

@Component
public class AssetCategoryMapper {

    public AssetCategory toDomain(AssetCategoryJpaEntity e) {
        if (e == null) return null;
        return AssetCategory.builder()
                .id(e.getId()).assetCatName(e.getAssetCatName())
                .assetCatDescription(e.getAssetCatDescription())
                .parentCategoryId(e.getParentCategoryId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public AssetCategoryJpaEntity toJpa(AssetCategory d) {
        if (d == null) return null;
        AssetCategoryJpaEntity e = AssetCategoryJpaEntity.builder()
                .assetCatName(d.getAssetCatName()).assetCatDescription(d.getAssetCatDescription())
                .parentCategoryId(d.getParentCategoryId().orElse(null))
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
