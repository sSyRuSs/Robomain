package com.example.Robomain.application.asset_category.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListAssetCategoriesQuery {
    private int page = 0;
    private int size = 10;
    private String search;
    private UUID parentId; // null = all, UUID = filter by parent
}
