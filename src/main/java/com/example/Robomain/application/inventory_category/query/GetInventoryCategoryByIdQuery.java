package com.example.Robomain.application.inventory_category.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetInventoryCategoryByIdQuery {
    private final UUID categoryId;
}
