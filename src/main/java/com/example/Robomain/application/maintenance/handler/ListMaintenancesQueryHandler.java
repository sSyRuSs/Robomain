package com.example.Robomain.application.maintenance.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.maintenance.dto.MaintenanceDto;
import com.example.Robomain.application.maintenance.mapper.MaintenanceDtoMapper;
import com.example.Robomain.application.maintenance.query.ListMaintenancesQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.maintenance.repository.IMaintenanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListMaintenancesQueryHandler {

    private final IMaintenanceRepository maintenanceRepository;
    private final MaintenanceDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<MaintenanceDto> handle(ListMaintenancesQuery query) {
        var list = maintenanceRepository.search(query.getSearch(), query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = maintenanceRepository.count(query.getSearch());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

