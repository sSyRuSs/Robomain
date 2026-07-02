package com.example.Robomain.application.asset.command;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateAssetCommand {
    @NotBlank(message = "Asset name is required")
    private String assetName;
    private String assetDescription;
    private String assetCode;
    private Integer assetQuantity;
    private UUID facilityId;
    private UUID assetCategoryId;
    private UUID parentAssetId;
}
