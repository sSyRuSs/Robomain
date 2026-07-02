package com.example.Robomain.application.inventory_category.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class InventoryCategoryDto {
    private UUID id;
    private String categoryCode;
    private String categoryName;
    private String description;
    private UUID parentCategoryId;
    private UUID enterpriseId;
}
