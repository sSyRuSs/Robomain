package com.example.Robomain.application.asset_status.dto;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumAssetStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class AssetStatusHistoryDto {
    private UUID id;
    private UUID assetId;
    private UUID assetStatusId;
    private EnumAssetStatus status;
    private String reason;
    private Date changedAt;
}
