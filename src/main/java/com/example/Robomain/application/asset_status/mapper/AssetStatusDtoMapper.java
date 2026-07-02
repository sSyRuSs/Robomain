package com.example.Robomain.application.asset_status.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.asset_status.dto.AssetStatusDto;
import com.example.Robomain.domain.asset_status.model.AssetStatus;

@Component
public class AssetStatusDtoMapper {

    public AssetStatusDto toDto(AssetStatus s) {
        return AssetStatusDto.builder()
                .id(s.getId()).assetId(s.getAssetId()).status(s.getStatus())
                .reason(s.getReason()).fromDate(s.getFromDate())
                .build();
    }
}
