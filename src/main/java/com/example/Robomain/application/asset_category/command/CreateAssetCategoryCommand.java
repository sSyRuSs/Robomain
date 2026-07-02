package com.example.Robomain.application.asset_category.command;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateAssetCategoryCommand {
    @NotBlank(message = "Asset category name is required")
    private String assetCatName;
    private String assetCatDescription;
    private UUID parentCategoryId;
}
