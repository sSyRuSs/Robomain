package com.example.Robomain.application.asset.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class AssetDto {
    private UUID id;
    private String assetName;
    private String assetDescription;
    private String assetCode;
    private Integer assetQuantity;
    private UUID facilityId;
    private UUID assetCategoryId;
    private UUID parentAssetId;
}
