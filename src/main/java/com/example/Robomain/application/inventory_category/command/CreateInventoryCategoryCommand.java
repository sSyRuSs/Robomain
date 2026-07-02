package com.example.Robomain.application.inventory_category.command;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateInventoryCategoryCommand {
    @NotBlank(message = "Category code is required")
    private String categoryCode;
    @NotBlank(message = "Category name is required")
    private String categoryName;
    private String description;
    private UUID parentCategoryId;
    @NotNull(message = "Enterprise ID is required")
    private UUID enterpriseId;
}
