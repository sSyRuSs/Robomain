package com.example.Robomain.domain.enterprise.event;

import java.util.UUID;

/** Published when an enterprise is registered in the system. */
public record EnterpriseCreatedEvent(UUID enterpriseId, String enterpriseName) {}
