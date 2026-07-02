package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.asset_status.command.SetAssetStatusCommand;
import com.example.Robomain.application.asset_status.dto.AssetStatusDto;
import com.example.Robomain.application.asset_status.dto.AssetStatusHistoryDto;
import com.example.Robomain.application.asset_status.handler.*;
import com.example.Robomain.application.asset_status.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.ASSET_STATUS_PATH)
@RequiredArgsConstructor
public class AssetStatusController {

    private final SetAssetStatusCommandHandler setHandler;
    private final GetAssetStatusQueryHandler getHandler;
    private final ListAssetStatusHistoryQueryHandler historyHandler;

    /** Set (create or update) the current status of an asset. */
    @PostMapping("/{assetId}")
    public ResponseEntity<ApiResponse<AssetStatusDto>> setStatus(
            @PathVariable UUID assetId, @Valid @RequestBody SetAssetStatusCommand cmd) {
        cmd.setAssetId(assetId);
        setHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getHandler.handle(new GetAssetStatusQuery(assetId))));
    }

    /** Get the current status for a given asset. */
    @GetMapping("/{assetId}")
    public ResponseEntity<ApiResponse<AssetStatusDto>> getStatus(@PathVariable UUID assetId) {
        return ResponseEntity.ok(ApiResponse.success(getHandler.handle(new GetAssetStatusQuery(assetId))));
    }

    /** Get paginated status change history for a given asset. */
    @GetMapping("/{assetId}/history")
    public ResponseEntity<ApiResponse<PaginationResponse<AssetStatusHistoryDto>>> getHistory(
            @PathVariable UUID assetId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(
                historyHandler.handle(new ListAssetStatusHistoryQuery(assetId, page, size))));
    }
}
