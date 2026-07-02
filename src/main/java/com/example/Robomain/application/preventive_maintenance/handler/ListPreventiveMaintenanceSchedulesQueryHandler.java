package com.example.Robomain.application.preventive_maintenance.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.preventive_maintenance.dto.PreventiveMaintenanceScheduleDto;
import com.example.Robomain.application.preventive_maintenance.mapper.PreventiveMaintenanceScheduleDtoMapper;
import com.example.Robomain.application.preventive_maintenance.query.ListPreventiveMaintenanceSchedulesQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.preventive_maintenance.repository.IPreventiveMaintenanceScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListPreventiveMaintenanceSchedulesQueryHandler {

    private final IPreventiveMaintenanceScheduleRepository scheduleRepository;
    private final PreventiveMaintenanceScheduleDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<PreventiveMaintenanceScheduleDto> handle(ListPreventiveMaintenanceSchedulesQuery query) {
        var items = scheduleRepository.search(query.getKeyword(), query.getAssetId(), query.getMaintenanceId(),
                query.getStatus(), query.getEnterpriseId(), query.getPage(), query.getSize());
        var total = scheduleRepository.count(query.getKeyword(), query.getEnterpriseId());
        var dtos = items.stream().map(dtoMapper::toDto).toList();
        return PaginationResponse.of(dtos, total, query.getPage(), query.getSize());
    }
}

