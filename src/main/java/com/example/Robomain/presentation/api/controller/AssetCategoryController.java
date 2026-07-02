package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.asset_category.command.CreateAssetCategoryCommand;
import com.example.Robomain.application.asset_category.command.UpdateAssetCategoryCommand;
import com.example.Robomain.application.asset_category.dto.AssetCategoryDto;
import com.example.Robomain.application.asset_category.handler.*;
import com.example.Robomain.application.asset_category.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.ASSET_CATEGORY_PATH)
@RequiredArgsConstructor
public class AssetCategoryController {

    private final CreateAssetCategoryCommandHandler createHandler;
    private final UpdateAssetCategoryCommandHandler updateHandler;
    private final GetAssetCategoryByIdQueryHandler getByIdHandler;
    private final ListAssetCategoriesQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<AssetCategoryDto>> create(
            @Valid @RequestBody CreateAssetCategoryCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetAssetCategoryByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetCategoryDto>> update(
            @PathVariable UUID id, @RequestBody UpdateAssetCategoryCommand cmd) {
        cmd.setCategoryId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetAssetCategoryByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetCategoryDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(
                getByIdHandler.handle(new GetAssetCategoryByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<AssetCategoryDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UUID parentId) {
        return ResponseEntity.ok(ApiResponse.success(
                listHandler.handle(new ListAssetCategoriesQuery(page, size, search, parentId))));
    }
}
