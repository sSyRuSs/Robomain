package com.example.Robomain.application.maintenance.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class MaintenanceDto {
    private UUID id;
    private String maintenanceName;
}
