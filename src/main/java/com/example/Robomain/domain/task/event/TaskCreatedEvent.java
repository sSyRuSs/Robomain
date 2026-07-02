package com.example.Robomain.domain.task.event;

import java.util.UUID;

/** Published when a new Task is created inside a WorkOrder. */
public record TaskCreatedEvent(UUID taskId, String taskName, UUID workOrderId) {}
