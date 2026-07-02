package com.example.Robomain.application.asset.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetAssetByIdQuery {
    private final UUID assetId;
}
