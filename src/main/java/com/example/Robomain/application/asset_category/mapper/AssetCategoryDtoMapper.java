package com.example.Robomain.application.asset_category.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.asset_category.dto.AssetCategoryDto;
import com.example.Robomain.domain.asset_category.model.AssetCategory;

@Component
public class AssetCategoryDtoMapper {

    public AssetCategoryDto toDto(AssetCategory c) {
        return AssetCategoryDto.builder()
                .id(c.getId()).assetCatName(c.getAssetCatName())
                .assetCatDescription(c.getAssetCatDescription())
                .parentCategoryId(c.getParentCategoryId().orElse(null))
                .build();
    }
}
