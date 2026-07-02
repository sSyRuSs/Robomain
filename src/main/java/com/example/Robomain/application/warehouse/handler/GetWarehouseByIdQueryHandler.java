package com.example.Robomain.application.warehouse.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.warehouse.dto.WarehouseDto;
import com.example.Robomain.application.warehouse.mapper.WarehouseDtoMapper;
import com.example.Robomain.application.warehouse.query.GetWarehouseByIdQuery;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.warehouse.repository.IWarehouseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetWarehouseByIdQueryHandler {

    private final IWarehouseRepository warehouseRepository;
    private final WarehouseDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public WarehouseDto handle(GetWarehouseByIdQuery query) {
        return warehouseRepository.findById(query.getWarehouseId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", query.getWarehouseId()));
    }
}

