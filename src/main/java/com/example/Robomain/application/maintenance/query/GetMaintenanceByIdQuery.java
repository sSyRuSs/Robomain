package com.example.Robomain.application.maintenance.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetMaintenanceByIdQuery {
    private final UUID maintenanceId;
}
