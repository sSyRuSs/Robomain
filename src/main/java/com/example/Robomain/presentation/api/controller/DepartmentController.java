package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.department.command.CreateDepartmentCommand;
import com.example.Robomain.application.department.command.UpdateDepartmentCommand;
import com.example.Robomain.application.department.dto.DepartmentDto;
import com.example.Robomain.application.department.handler.*;
import com.example.Robomain.application.department.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.DEPARTMENT_PATH)
@RequiredArgsConstructor
public class DepartmentController {

    private final CreateDepartmentCommandHandler createHandler;
    private final UpdateDepartmentCommandHandler updateHandler;
    private final GetDepartmentByIdQueryHandler getByIdHandler;
    private final ListDepartmentsQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentDto>> create(@Valid @RequestBody CreateDepartmentCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetDepartmentByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentDto>> update(
            @PathVariable UUID id, @Valid @RequestBody UpdateDepartmentCommand cmd) {
        cmd.setDepartmentId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetDepartmentByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetDepartmentByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<DepartmentDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(ApiResponse.success(
                listHandler.handle(new ListDepartmentsQuery(page, size, search))));
    }
}
