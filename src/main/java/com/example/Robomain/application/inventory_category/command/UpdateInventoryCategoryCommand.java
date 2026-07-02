package com.example.Robomain.application.inventory_category.command;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateInventoryCategoryCommand {
    private UUID categoryId;
    private String categoryName;
    private String description;
}
