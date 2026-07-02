package com.example.Robomain.application.warehouse.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.warehouse.command.CreateWarehouseCommand;
import com.example.Robomain.application.warehouse.mapper.WarehouseDtoMapper;
import com.example.Robomain.domain.shared.exception.ConflictException;
import com.example.Robomain.domain.warehouse.model.Warehouse;
import com.example.Robomain.domain.warehouse.repository.IWarehouseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateWarehouseCommandHandler {

    private final IWarehouseRepository warehouseRepository;
    private final WarehouseDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateWarehouseCommand command) {
        if (warehouseRepository.existsByWarehouseCode(command.getWarehouseCode())) {
            throw new ConflictException("Warehouse", "code", command.getWarehouseCode());
        }

        Warehouse warehouse = Warehouse.create(
                command.getWarehouseCode(), command.getWarehouseName(),
                command.getType(), command.getEnterpriseId());

        warehouse.update(null, command.getAddress(), command.getManagerName(),
                command.getContactPhone(), command.getContactEmail());

        return warehouseRepository.save(warehouse).getId();
    }
}
