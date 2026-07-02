package com.example.Robomain.application.maintenance.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.maintenance.command.CreateMaintenanceCommand;
import com.example.Robomain.application.maintenance.mapper.MaintenanceDtoMapper;
import com.example.Robomain.domain.maintenance.model.Maintenance;
import com.example.Robomain.domain.maintenance.repository.IMaintenanceRepository;
import com.example.Robomain.domain.shared.exception.ConflictException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateMaintenanceCommandHandler {

    private final IMaintenanceRepository maintenanceRepository;
    private final MaintenanceDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateMaintenanceCommand command) {
        if (maintenanceRepository.existsByMaintenanceName(command.getMaintenanceName())) {
            throw new ConflictException("Maintenance", "name", command.getMaintenanceName());
        }
        return maintenanceRepository.save(Maintenance.create(command.getMaintenanceName())).getId();
    }
}
