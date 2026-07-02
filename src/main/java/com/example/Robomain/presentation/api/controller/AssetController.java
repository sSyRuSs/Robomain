package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.asset.command.CreateAssetCommand;
import com.example.Robomain.application.asset.command.UpdateAssetCommand;
import com.example.Robomain.application.asset.dto.AssetDto;
import com.example.Robomain.application.asset.handler.*;
import com.example.Robomain.application.asset.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.ASSET_PATH)
@RequiredArgsConstructor
public class AssetController {

    private final CreateAssetCommandHandler createHandler;
    private final UpdateAssetCommandHandler updateHandler;
    private final GetAssetByIdQueryHandler getByIdHandler;
    private final ListAssetsQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<AssetDto>> create(@Valid @RequestBody CreateAssetCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetAssetByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetDto>> update(
            @PathVariable UUID id, @RequestBody UpdateAssetCommand cmd) {
        cmd.setAssetId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetAssetByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetAssetByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<AssetDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UUID facilityId,
            @RequestParam(required = false) UUID categoryId) {
        return ResponseEntity.ok(ApiResponse.success(
                listHandler.handle(new ListAssetsQuery(page, size, search, facilityId, categoryId))));
    }
}
