package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.work_order.command.CreateWorkOrderCommand;
import com.example.Robomain.application.work_order.command.UpdateWorkOrderCommand;
import com.example.Robomain.application.work_order.dto.WorkOrderDto;
import com.example.Robomain.application.work_order.handler.*;
import com.example.Robomain.application.work_order.query.*;
import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.WORK_ORDER_PATH)
@RequiredArgsConstructor
public class WorkOrderController {

    private final CreateWorkOrderCommandHandler createHandler;
    private final UpdateWorkOrderCommandHandler updateHandler;
    private final GetWorkOrderByIdQueryHandler getByIdHandler;
    private final ListWorkOrdersQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<WorkOrderDto>> create(@Valid @RequestBody CreateWorkOrderCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetWorkOrderByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkOrderDto>> update(
            @PathVariable UUID id, @RequestBody UpdateWorkOrderCommand cmd) {
        cmd.setWorkOrderId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetWorkOrderByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkOrderDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetWorkOrderByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<WorkOrderDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) EnumStatus status,
            @RequestParam(required = false) EnumPriority priority,
            @RequestParam(required = false) UUID maintenanceId,
            @RequestParam(required = false) UUID assetId) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListWorkOrdersQuery(page, size, search, status, priority, maintenanceId, assetId))));
    }
}
