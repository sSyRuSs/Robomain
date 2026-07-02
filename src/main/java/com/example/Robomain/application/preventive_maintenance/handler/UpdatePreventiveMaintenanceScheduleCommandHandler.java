package com.example.Robomain.application.preventive_maintenance.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.preventive_maintenance.command.UpdatePreventiveMaintenanceScheduleCommand;
import com.example.Robomain.application.preventive_maintenance.mapper.PreventiveMaintenanceScheduleDtoMapper;
import com.example.Robomain.domain.preventive_maintenance.repository.IPreventiveMaintenanceScheduleRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdatePreventiveMaintenanceScheduleCommandHandler {

    private final IPreventiveMaintenanceScheduleRepository scheduleRepository;
    private final PreventiveMaintenanceScheduleDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdatePreventiveMaintenanceScheduleCommand cmd) {
        var schedule = scheduleRepository.findById(cmd.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("PreventiveMaintenanceSchedule", cmd.getScheduleId()));
        schedule.update(cmd.getScheduleName(), cmd.getDescription(), cmd.getFrequency(),
                cmd.getIntervalCount(), cmd.getNextDueDate(), cmd.getEstimatedDurationHours(), cmd.getLeadTimeDays());
        return scheduleRepository.save(schedule).getId();
    }
}
