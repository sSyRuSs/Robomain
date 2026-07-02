package com.example.Robomain.domain.work_order.event;

import java.util.UUID;

/** Published when a new WorkOrder is created. */
public record WorkOrderCreatedEvent(UUID workOrderId, String workOrderName, UUID maintenanceId) {}
