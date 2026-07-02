package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.preventive_maintenance.command.*;
import com.example.Robomain.application.preventive_maintenance.dto.PreventiveMaintenanceScheduleDto;
import com.example.Robomain.application.preventive_maintenance.handler.*;
import com.example.Robomain.application.preventive_maintenance.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.shared.enums.EnumPreventiveScheduleStatus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.PREVENTIVE_MAINTENANCE_PATH)
@RequiredArgsConstructor
public class PreventiveMaintenanceController {

    private final CreatePreventiveMaintenanceScheduleCommandHandler createHandler;
    private final UpdatePreventiveMaintenanceScheduleCommandHandler updateHandler;
    private final GetPreventiveMaintenanceScheduleByIdQueryHandler getByIdHandler;
    private final ListPreventiveMaintenanceSchedulesQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<PreventiveMaintenanceScheduleDto>> create(
            @Valid @RequestBody CreatePreventiveMaintenanceScheduleCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetPreventiveMaintenanceScheduleByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PreventiveMaintenanceScheduleDto>> update(
            @PathVariable UUID id, @RequestBody UpdatePreventiveMaintenanceScheduleCommand cmd) {
        cmd.setScheduleId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetPreventiveMaintenanceScheduleByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PreventiveMaintenanceScheduleDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(
                getByIdHandler.handle(new GetPreventiveMaintenanceScheduleByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<PreventiveMaintenanceScheduleDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UUID assetId,
            @RequestParam(required = false) UUID maintenanceId,
            @RequestParam(required = false) EnumPreventiveScheduleStatus status,
            @RequestParam(required = false) UUID enterpriseId) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListPreventiveMaintenanceSchedulesQuery(page, size, search, assetId, maintenanceId,
                        status, enterpriseId))));
    }
}
