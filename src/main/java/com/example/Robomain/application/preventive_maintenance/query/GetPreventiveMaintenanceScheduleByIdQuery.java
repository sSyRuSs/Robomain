package com.example.Robomain.application.preventive_maintenance.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetPreventiveMaintenanceScheduleByIdQuery {
    private final UUID scheduleId;
}
