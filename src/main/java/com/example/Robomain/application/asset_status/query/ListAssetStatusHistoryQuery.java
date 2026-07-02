package com.example.Robomain.application.asset_status.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListAssetStatusHistoryQuery {
    private UUID assetId;
    private int page = 0;
    private int size = 10;
}
