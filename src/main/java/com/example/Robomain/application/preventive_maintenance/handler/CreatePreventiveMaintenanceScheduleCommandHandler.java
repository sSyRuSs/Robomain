package com.example.Robomain.application.preventive_maintenance.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.preventive_maintenance.command.CreatePreventiveMaintenanceScheduleCommand;
import com.example.Robomain.application.preventive_maintenance.mapper.PreventiveMaintenanceScheduleDtoMapper;
import com.example.Robomain.domain.preventive_maintenance.model.PreventiveMaintenanceSchedule;
import com.example.Robomain.domain.preventive_maintenance.repository.IPreventiveMaintenanceScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePreventiveMaintenanceScheduleCommandHandler {

    private final IPreventiveMaintenanceScheduleRepository scheduleRepository;
    private final PreventiveMaintenanceScheduleDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreatePreventiveMaintenanceScheduleCommand cmd) {
        PreventiveMaintenanceSchedule schedule = PreventiveMaintenanceSchedule.create(
                cmd.getScheduleName(), cmd.getAssetId(), cmd.getMaintenanceId(),
                cmd.getFrequency(), cmd.getIntervalCount(), cmd.getNextDueDate(),
                cmd.getEstimatedDurationHours(), cmd.getLeadTimeDays(), cmd.getEnterpriseId());
        if (cmd.getDescription() != null) {
            schedule = PreventiveMaintenanceSchedule.builder()
                    .id(schedule.getId()).scheduleName(schedule.getScheduleName()).description(cmd.getDescription())
                    .assetId(schedule.getAssetId()).maintenanceId(schedule.getMaintenanceId())
                    .frequency(schedule.getFrequency()).intervalCount(schedule.getIntervalCount())
                    .nextDueDate(schedule.getNextDueDate()).estimatedDurationHours(schedule.getEstimatedDurationHours())
                    .leadTimeDays(schedule.getLeadTimeDays()).status(schedule.getStatus())
                    .enterpriseId(schedule.getEnterpriseId()).build();
        }
        return scheduleRepository.save(schedule).getId();
    }
}
