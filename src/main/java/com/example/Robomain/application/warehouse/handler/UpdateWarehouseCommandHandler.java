package com.example.Robomain.application.warehouse.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.warehouse.command.UpdateWarehouseCommand;
import com.example.Robomain.application.warehouse.mapper.WarehouseDtoMapper;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.warehouse.repository.IWarehouseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateWarehouseCommandHandler {

    private final IWarehouseRepository warehouseRepository;
    private final WarehouseDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateWarehouseCommand command) {
        var warehouse = warehouseRepository.findById(command.getWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", command.getWarehouseId()));
        warehouse.update(command.getWarehouseName(), command.getAddress(),
                command.getManagerName(), command.getContactPhone(), command.getContactEmail());
        return warehouseRepository.save(warehouse).getId();
    }
}
