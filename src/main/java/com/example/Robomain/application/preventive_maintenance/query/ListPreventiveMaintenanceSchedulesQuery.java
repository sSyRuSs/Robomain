package com.example.Robomain.application.preventive_maintenance.query;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPreventiveScheduleStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ListPreventiveMaintenanceSchedulesQuery {
    private final int page;
    private final int size;
    private final String keyword;
    private final UUID assetId;
    private final UUID maintenanceId;
    private final EnumPreventiveScheduleStatus status;
    private final UUID enterpriseId;
}
