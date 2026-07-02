package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.inventory_category.command.CreateInventoryCategoryCommand;
import com.example.Robomain.application.inventory_category.command.UpdateInventoryCategoryCommand;
import com.example.Robomain.application.inventory_category.dto.InventoryCategoryDto;
import com.example.Robomain.application.inventory_category.handler.*;
import com.example.Robomain.application.inventory_category.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.INVENTORY_CATEGORY_PATH)
@RequiredArgsConstructor
public class InventoryCategoryController {

    private final CreateInventoryCategoryCommandHandler createHandler;
    private final UpdateInventoryCategoryCommandHandler updateHandler;
    private final GetInventoryCategoryByIdQueryHandler getByIdHandler;
    private final ListInventoryCategoriesQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<InventoryCategoryDto>> create(
            @Valid @RequestBody CreateInventoryCategoryCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetInventoryCategoryByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryCategoryDto>> update(
            @PathVariable UUID id, @RequestBody UpdateInventoryCategoryCommand cmd) {
        cmd.setCategoryId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetInventoryCategoryByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryCategoryDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(
                getByIdHandler.handle(new GetInventoryCategoryByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<InventoryCategoryDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UUID enterpriseId,
            @RequestParam(required = false) UUID parentId) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListInventoryCategoriesQuery(page, size, search, enterpriseId, parentId))));
    }
}
