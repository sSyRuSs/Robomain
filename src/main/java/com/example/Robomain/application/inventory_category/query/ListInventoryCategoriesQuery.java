package com.example.Robomain.application.inventory_category.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListInventoryCategoriesQuery {
    private int page = 0;
    private int size = 10;
    private String search;
    private UUID enterpriseId;
    private UUID parentId;
}
