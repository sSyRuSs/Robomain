package com.example.Robomain.application.inventory.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetInventoryByIdQuery {
    private final UUID inventoryId;
}
