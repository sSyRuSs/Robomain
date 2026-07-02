package com.example.Robomain.application.maintenance.command;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateMaintenanceCommand {
    private UUID maintenanceId;
    @NotBlank(message = "Maintenance name is required")
    private String maintenanceName;
}
