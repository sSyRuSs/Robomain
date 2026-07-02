package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.enterprise.command.CreateEnterpriseCommand;
import com.example.Robomain.application.enterprise.command.UpdateEnterpriseCommand;
import com.example.Robomain.application.enterprise.dto.EnterpriseDto;
import com.example.Robomain.application.enterprise.handler.*;
import com.example.Robomain.application.enterprise.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.ENTERPRISE_PATH)
@RequiredArgsConstructor
public class EnterpriseController {

    private final CreateEnterpriseCommandHandler createHandler;
    private final UpdateEnterpriseCommandHandler updateHandler;
    private final GetEnterpriseByIdQueryHandler getByIdHandler;
    private final ListEnterprisesQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<EnterpriseDto>> create(@Valid @RequestBody CreateEnterpriseCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetEnterpriseByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EnterpriseDto>> update(
            @PathVariable UUID id, @RequestBody UpdateEnterpriseCommand cmd) {
        cmd.setEnterpriseId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetEnterpriseByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EnterpriseDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetEnterpriseByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<EnterpriseDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(ApiResponse.success(
                listHandler.handle(new ListEnterprisesQuery(page, size, search))));
    }
}
