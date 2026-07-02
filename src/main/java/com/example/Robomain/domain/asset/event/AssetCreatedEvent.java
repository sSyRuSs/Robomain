package com.example.Robomain.domain.asset.event;

import java.util.UUID;

/**
 * Published when a new asset is successfully created.
 * Downstream handlers can react (e.g., create default AssetStatus).
 */
public record AssetCreatedEvent(UUID assetId, String assetName, UUID facilityId) {}
