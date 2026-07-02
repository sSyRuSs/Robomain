package com.example.Robomain.application.asset_category.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class AssetCategoryDto {
    private UUID id;
    private String assetCatName;
    private String assetCatDescription;
    private UUID parentCategoryId;
}
