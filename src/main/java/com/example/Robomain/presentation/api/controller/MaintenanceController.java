package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.maintenance.command.CreateMaintenanceCommand;
import com.example.Robomain.application.maintenance.command.UpdateMaintenanceCommand;
import com.example.Robomain.application.maintenance.dto.MaintenanceDto;
import com.example.Robomain.application.maintenance.handler.*;
import com.example.Robomain.application.maintenance.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.MAINTENANCE_PATH)
@RequiredArgsConstructor
public class MaintenanceController {

    private final CreateMaintenanceCommandHandler createHandler;
    private final UpdateMaintenanceCommandHandler updateHandler;
    private final GetMaintenanceByIdQueryHandler getByIdHandler;
    private final ListMaintenancesQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<MaintenanceDto>> create(@Valid @RequestBody CreateMaintenanceCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetMaintenanceByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintenanceDto>> update(
            @PathVariable UUID id, @Valid @RequestBody UpdateMaintenanceCommand cmd) {
        cmd.setMaintenanceId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetMaintenanceByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintenanceDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetMaintenanceByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<MaintenanceDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(ApiResponse.success(
                listHandler.handle(new ListMaintenancesQuery(page, size, search))));
    }
}
