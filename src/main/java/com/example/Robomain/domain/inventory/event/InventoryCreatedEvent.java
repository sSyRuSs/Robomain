package com.example.Robomain.domain.inventory.event;

import java.util.UUID;

/** Published when a new inventory item is registered. */
public record InventoryCreatedEvent(UUID inventoryId, String itemCode, String itemName, UUID enterpriseId) {}
