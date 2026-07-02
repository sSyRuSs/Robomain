package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.warehouse.command.CreateWarehouseCommand;
import com.example.Robomain.application.warehouse.command.UpdateWarehouseCommand;
import com.example.Robomain.application.warehouse.dto.WarehouseDto;
import com.example.Robomain.application.warehouse.handler.*;
import com.example.Robomain.application.warehouse.query.*;
import com.example.Robomain.domain.shared.enums.EnumWarehouseType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.WAREHOUSE_PATH)
@RequiredArgsConstructor
public class WarehouseController {

    private final CreateWarehouseCommandHandler createHandler;
    private final UpdateWarehouseCommandHandler updateHandler;
    private final GetWarehouseByIdQueryHandler getByIdHandler;
    private final ListWarehousesQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<WarehouseDto>> create(@Valid @RequestBody CreateWarehouseCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetWarehouseByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseDto>> update(
            @PathVariable UUID id, @RequestBody UpdateWarehouseCommand cmd) {
        cmd.setWarehouseId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetWarehouseByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetWarehouseByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<WarehouseDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UUID enterpriseId,
            @RequestParam(required = false) EnumWarehouseType type) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListWarehousesQuery(page, size, search, enterpriseId, type))));
    }
}
