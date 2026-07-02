package com.example.Robomain.application.asset.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateAssetCommand {
    private UUID assetId;
    private String assetName;
    private String assetDescription;
    private String assetCode;
    private Integer assetQuantity;
}
