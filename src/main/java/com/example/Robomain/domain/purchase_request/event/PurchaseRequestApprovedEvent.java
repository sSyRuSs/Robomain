package com.example.Robomain.domain.purchase_request.event;

import java.util.UUID;

/** Published when a PurchaseRequest is approved — triggers downstream stock-reorder flow. */
public record PurchaseRequestApprovedEvent(
        UUID requestId, String requestCode,
        UUID inventoryId, int quantity, UUID vendorId) {}
