package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.stock_movement.command.RecordStockMovementCommand;
import com.example.Robomain.application.stock_movement.dto.StockMovementDto;
import com.example.Robomain.application.stock_movement.handler.*;
import com.example.Robomain.application.stock_movement.query.*;
import com.example.Robomain.domain.shared.enums.EnumMovementType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.INVENTORY_PATH + "/movements")
@RequiredArgsConstructor
public class StockMovementController {

    private final RecordStockMovementCommandHandler recordHandler;
    private final GetStockMovementByIdQueryHandler getByIdHandler;
    private final ListStockMovementsQueryHandler listHandler;

    /** Record a stock IN, OUT, TRANSFER, ADJUSTMENT, or RETURN movement. */
    @PostMapping
    public ResponseEntity<ApiResponse<StockMovementDto>> record(
            @Valid @RequestBody RecordStockMovementCommand cmd) {
        UUID id = recordHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetStockMovementByIdQuery(id))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StockMovementDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetStockMovementByIdQuery(id))));
    }

    /** Get paginated stock movements for a specific inventory item. */
    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<StockMovementDto>>> list(
            @RequestParam UUID inventoryId,
            @RequestParam(required = false) EnumMovementType type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListStockMovementsQuery(inventoryId, type, page, size))));
    }
}
