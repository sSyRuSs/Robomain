package com.example.Robomain.application.maintenance.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.maintenance.command.UpdateMaintenanceCommand;
import com.example.Robomain.application.maintenance.mapper.MaintenanceDtoMapper;
import com.example.Robomain.domain.maintenance.repository.IMaintenanceRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateMaintenanceCommandHandler {

    private final IMaintenanceRepository maintenanceRepository;
    private final MaintenanceDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateMaintenanceCommand command) {
        var maintenance = maintenanceRepository.findById(command.getMaintenanceId())
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", command.getMaintenanceId()));
        maintenance.rename(command.getMaintenanceName());
        return maintenanceRepository.save(maintenance).getId();
    }
}
