package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.asset_status.model.AssetStatus;
import com.example.Robomain.infrastructure.persistence.entity.AssetStatusJpaEntity;

@Component
public class AssetStatusMapper {

    public AssetStatus toDomain(AssetStatusJpaEntity e) {
        if (e == null) return null;
        return AssetStatus.builder()
                .id(e.getId()).assetId(e.getAssetId()).status(e.getStatus())
                .reason(e.getReason()).fromDate(e.getFromDate())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public AssetStatusJpaEntity toJpa(AssetStatus d) {
        if (d == null) return null;
        AssetStatusJpaEntity e = AssetStatusJpaEntity.builder()
                .assetId(d.getAssetId()).status(d.getStatus())
                .reason(d.getReason()).fromDate(d.getFromDate())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
