package com.example.Robomain.application.asset_category.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateAssetCategoryCommand {
    private UUID categoryId;
    private String assetCatName;
    private String assetCatDescription;
}
