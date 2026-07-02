package com.example.Robomain.application.preventive_maintenance.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.preventive_maintenance.dto.PreventiveMaintenanceScheduleDto;
import com.example.Robomain.application.preventive_maintenance.mapper.PreventiveMaintenanceScheduleDtoMapper;
import com.example.Robomain.application.preventive_maintenance.query.GetPreventiveMaintenanceScheduleByIdQuery;
import com.example.Robomain.domain.preventive_maintenance.repository.IPreventiveMaintenanceScheduleRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetPreventiveMaintenanceScheduleByIdQueryHandler {

    private final IPreventiveMaintenanceScheduleRepository scheduleRepository;
    private final PreventiveMaintenanceScheduleDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PreventiveMaintenanceScheduleDto handle(GetPreventiveMaintenanceScheduleByIdQuery query) {
        return scheduleRepository.findById(query.getScheduleId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("PreventiveMaintenanceSchedule", query.getScheduleId()));
    }
}

