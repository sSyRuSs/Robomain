package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.inventory.command.CreateInventoryCommand;
import com.example.Robomain.application.inventory.command.UpdateInventoryCommand;
import com.example.Robomain.application.inventory.dto.InventoryDto;
import com.example.Robomain.application.inventory.handler.*;
import com.example.Robomain.application.inventory.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.shared.enums.EnumInventoryStatus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.INVENTORY_PATH)
@RequiredArgsConstructor
public class InventoryController {

    private final CreateInventoryCommandHandler createHandler;
    private final UpdateInventoryCommandHandler updateHandler;
    private final GetInventoryByIdQueryHandler getByIdHandler;
    private final ListInventoryQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<InventoryDto>> create(@Valid @RequestBody CreateInventoryCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetInventoryByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryDto>> update(
            @PathVariable UUID id, @RequestBody UpdateInventoryCommand cmd) {
        cmd.setInventoryId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetInventoryByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetInventoryByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<InventoryDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID warehouseId,
            @RequestParam(required = false) EnumInventoryStatus status,
            @RequestParam(required = false) UUID enterpriseId) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListInventoryQuery(page, size, search, categoryId, warehouseId, status, enterpriseId))));
    }
}
