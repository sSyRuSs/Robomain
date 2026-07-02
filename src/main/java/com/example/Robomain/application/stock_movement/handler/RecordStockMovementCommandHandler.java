package com.example.Robomain.application.stock_movement.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.stock_movement.command.RecordStockMovementCommand;
import com.example.Robomain.application.stock_movement.mapper.StockMovementDtoMapper;
import com.example.Robomain.domain.inventory.model.Inventory;
import com.example.Robomain.domain.inventory.repository.IInventoryRepository;
import com.example.Robomain.domain.shared.enums.EnumMovementType;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.stock_movement.event.StockLevelChangedEvent;
import com.example.Robomain.domain.stock_movement.model.StockMovement;
import com.example.Robomain.domain.stock_movement.repository.IStockMovementRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordStockMovementCommandHandler {

    private final IInventoryRepository inventoryRepository;
    private final IStockMovementRepository movementRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final StockMovementDtoMapper dtoMapper;

    /**
     * Atomically adjusts inventory quantity and creates an immutable movement record.
     * Why atomic: quantity and audit log must always be consistent.
     */
    @Transactional
    public UUID handle(RecordStockMovementCommand command) {
        Inventory inventory = inventoryRepository.findById(command.getInventoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", command.getInventoryId()));

        int movementQty = applyMovement(inventory, command);
        Inventory saved = inventoryRepository.save(inventory);

        StockMovement movement = StockMovement.record(
                command.getMovementCode(), saved.getId(), command.getMovementType(),
                movementQty, saved.getQuantity(),
                command.getFromWarehouseId(), command.getToWarehouseId(), command.getReason(),
                command.getWorkOrderId(), command.getTaskId(), command.getPurchaseOrderId(),
                command.getProcessedBy(), saved.getUnitCost());

        StockMovement savedMovement = movementRepository.save(movement);

        // Notify downstream if stock falls to/below reorder point
        eventPublisher.publishEvent(new StockLevelChangedEvent(
                saved.getId(), saved.getItemCode(),
                saved.getQuantity(), saved.getReorderPoint(), saved.needsReorder()));

        return savedMovement.getId();
    }

    /**
     * Applies the movement to the inventory and returns the actual movement quantity.
     * ADJUSTMENT: quantity in command = new target → movement qty = |delta|.
     */
    private int applyMovement(Inventory inventory, RecordStockMovementCommand command) {
        return switch (command.getMovementType()) {
            case IN, RETURN -> {
                inventory.receiveStock(command.getQuantity());
                yield command.getQuantity();
            }
            case OUT, TRANSFER -> {
                inventory.issueStock(command.getQuantity());
                yield command.getQuantity();
            }
            case ADJUSTMENT -> {
                int delta = Math.abs(command.getQuantity() - inventory.getQuantity());
                inventory.adjustQuantity(command.getQuantity());
                yield delta;
            }
        };
    }

}
