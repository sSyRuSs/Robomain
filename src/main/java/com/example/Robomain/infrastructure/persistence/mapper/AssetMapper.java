package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.asset.model.Asset;
import com.example.Robomain.infrastructure.persistence.entity.AssetJpaEntity;

@Component
public class AssetMapper {

    public Asset toDomain(AssetJpaEntity e) {
        if (e == null) return null;
        return Asset.builder()
                .id(e.getId()).assetName(e.getAssetName()).assetDescription(e.getAssetDescription())
                .assetCode(e.getAssetCode()).assetQuantity(e.getAssetQuantity())
                .facilityId(e.getFacilityId()).assetCategoryId(e.getAssetCategoryId())
                .parentAssetId(e.getParentAssetId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public AssetJpaEntity toJpa(Asset d) {
        if (d == null) return null;
        AssetJpaEntity e = AssetJpaEntity.builder()
                .assetName(d.getAssetName()).assetDescription(d.getAssetDescription())
                .assetCode(d.getAssetCode()).assetQuantity(d.getAssetQuantity())
                .facilityId(d.getFacilityId()).assetCategoryId(d.getAssetCategoryId())
                .parentAssetId(d.getParentAssetId().orElse(null))
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
