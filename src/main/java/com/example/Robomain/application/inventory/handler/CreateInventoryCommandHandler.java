package com.example.Robomain.application.inventory.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.inventory.command.CreateInventoryCommand;
import com.example.Robomain.application.inventory.mapper.InventoryDtoMapper;
import com.example.Robomain.domain.inventory.event.InventoryCreatedEvent;
import com.example.Robomain.domain.inventory.model.Inventory;
import com.example.Robomain.domain.inventory.repository.IInventoryRepository;
import com.example.Robomain.domain.shared.exception.ConflictException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateInventoryCommandHandler {

    private final IInventoryRepository inventoryRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final InventoryDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateInventoryCommand command) {
        if (inventoryRepository.existsByItemCode(command.getItemCode())) {
            throw new ConflictException("Inventory", "itemCode", command.getItemCode());
        }

        Inventory inventory = Inventory.create(
                command.getItemCode(), command.getItemName(),
                command.getCategoryId(), command.getWarehouseId(), command.getEnterpriseId());

        inventory.update(null, command.getDescription(), command.getUnit(), command.getUnitCost(),
                command.getMinStockLevel(), command.getReorderPoint(), command.getReorderQuantity(),
                command.getBinLocation(), command.getPreferredSupplierId());

        Inventory saved = inventoryRepository.save(inventory);
        eventPublisher.publishEvent(new InventoryCreatedEvent(
                saved.getId(), saved.getItemCode(), saved.getItemName(), saved.getEnterpriseId()));
        return saved.getId();
    }
}
