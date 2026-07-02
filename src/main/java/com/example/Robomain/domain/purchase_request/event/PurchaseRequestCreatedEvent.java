package com.example.Robomain.domain.purchase_request.event;

import java.util.UUID;

/** Published when a new PurchaseRequest is created — allows notification and audit. */
public record PurchaseRequestCreatedEvent(
        UUID requestId, String requestCode,
        UUID inventoryId, int quantity, UUID enterpriseId) {}
