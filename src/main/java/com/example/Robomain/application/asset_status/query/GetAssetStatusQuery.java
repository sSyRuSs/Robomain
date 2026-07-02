package com.example.Robomain.application.asset_status.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetAssetStatusQuery {
    private final UUID assetId;
}
