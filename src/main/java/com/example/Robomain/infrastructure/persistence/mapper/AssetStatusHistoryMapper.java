package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.asset_status_history.model.AssetStatusHistory;
import com.example.Robomain.infrastructure.persistence.entity.AssetStatusHistoryJpaEntity;

@Component
public class AssetStatusHistoryMapper {

    public AssetStatusHistory toDomain(AssetStatusHistoryJpaEntity e) {
        if (e == null) return null;
        return AssetStatusHistory.builder()
                .id(e.getId()).assetId(e.getAssetId()).assetStatusId(e.getAssetStatusId())
                .status(e.getStatus()).reason(e.getReason()).changedAt(e.getChangedAt())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public AssetStatusHistoryJpaEntity toJpa(AssetStatusHistory d) {
        if (d == null) return null;
        AssetStatusHistoryJpaEntity e = AssetStatusHistoryJpaEntity.builder()
                .assetId(d.getAssetId()).assetStatusId(d.getAssetStatusId())
                .status(d.getStatus()).reason(d.getReason()).changedAt(d.getChangedAt())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
