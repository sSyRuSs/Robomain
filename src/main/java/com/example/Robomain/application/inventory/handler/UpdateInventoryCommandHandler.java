package com.example.Robomain.application.inventory.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.inventory.command.UpdateInventoryCommand;
import com.example.Robomain.application.inventory.mapper.InventoryDtoMapper;
import com.example.Robomain.domain.inventory.repository.IInventoryRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateInventoryCommandHandler {

    private final IInventoryRepository inventoryRepository;
    private final InventoryDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateInventoryCommand command) {
        var inventory = inventoryRepository.findById(command.getInventoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", command.getInventoryId()));

        inventory.update(command.getItemName(), command.getDescription(), command.getUnit(),
                command.getUnitCost(), command.getMinStockLevel(), command.getReorderPoint(),
                command.getReorderQuantity(), command.getBinLocation(), command.getPreferredSupplierId());

        if (command.getStatus() != null) inventory.changeStatus(command.getStatus());

        return inventoryRepository.save(inventory).getId();
    }
}
