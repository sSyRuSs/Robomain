package com.example.Robomain.application.warehouse.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.warehouse.dto.WarehouseDto;
import com.example.Robomain.application.warehouse.mapper.WarehouseDtoMapper;
import com.example.Robomain.application.warehouse.query.ListWarehousesQuery;
import com.example.Robomain.domain.warehouse.repository.IWarehouseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListWarehousesQueryHandler {

    private final IWarehouseRepository warehouseRepository;
    private final WarehouseDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<WarehouseDto> handle(ListWarehousesQuery query) {
        var list = warehouseRepository.search(
                        query.getSearch(), query.getEnterpriseId(), query.getType(),
                        query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = warehouseRepository.count(query.getSearch(), query.getEnterpriseId());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

