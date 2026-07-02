package com.example.Robomain.application.asset.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.asset.dto.AssetDto;
import com.example.Robomain.domain.asset.model.Asset;

@Component
public class AssetDtoMapper {

    public AssetDto toDto(Asset a) {
        return AssetDto.builder()
                .id(a.getId()).assetName(a.getAssetName()).assetDescription(a.getAssetDescription())
                .assetCode(a.getAssetCode()).assetQuantity(a.getAssetQuantity())
                .facilityId(a.getFacilityId()).assetCategoryId(a.getAssetCategoryId())
                .parentAssetId(a.getParentAssetId().orElse(null))
                .build();
    }
}
