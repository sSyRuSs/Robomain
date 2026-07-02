package com.example.Robomain.application.asset_category.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetAssetCategoryByIdQuery {
    private final UUID categoryId;
}
