package com.example.Robomain.application.maintenance.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.maintenance.dto.MaintenanceDto;
import com.example.Robomain.domain.maintenance.model.Maintenance;

@Component
public class MaintenanceDtoMapper {

    public MaintenanceDto toDto(Maintenance m) {
        return MaintenanceDto.builder().id(m.getId()).maintenanceName(m.getMaintenanceName()).build();
    }
}
