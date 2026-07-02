package com.example.Robomain.domain.facility.event;

import java.util.UUID;

/** Published when a new facility is created. */
public record FacilityCreatedEvent(UUID facilityId, UUID enterpriseId, String facilityName) {}
