package com.example.Robomain.application.maintenance.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.maintenance.dto.MaintenanceDto;
import com.example.Robomain.application.maintenance.mapper.MaintenanceDtoMapper;
import com.example.Robomain.application.maintenance.query.GetMaintenanceByIdQuery;
import com.example.Robomain.domain.maintenance.repository.IMaintenanceRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetMaintenanceByIdQueryHandler {

    private final IMaintenanceRepository maintenanceRepository;
    private final MaintenanceDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public MaintenanceDto handle(GetMaintenanceByIdQuery query) {
        return maintenanceRepository.findById(query.getMaintenanceId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", query.getMaintenanceId()));
    }
}

