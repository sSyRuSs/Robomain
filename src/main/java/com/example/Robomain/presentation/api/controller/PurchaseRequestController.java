package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.purchase_request.command.*;
import com.example.Robomain.application.purchase_request.dto.PurchaseRequestDto;
import com.example.Robomain.application.purchase_request.handler.*;
import com.example.Robomain.application.purchase_request.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.shared.enums.EnumPRStatus;
import com.example.Robomain.domain.shared.enums.EnumPRType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.PURCHASE_REQUEST_PATH)
@RequiredArgsConstructor
public class PurchaseRequestController {

    private final CreatePurchaseRequestCommandHandler createHandler;
    private final ApprovePurchaseRequestCommandHandler approveHandler;
    private final RejectPurchaseRequestCommandHandler rejectHandler;
    private final GetPurchaseRequestByIdQueryHandler getByIdHandler;
    private final ListPurchaseRequestsQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<PurchaseRequestDto>> create(
            @Valid @RequestBody CreatePurchaseRequestCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetPurchaseRequestByIdQuery(id))));
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<PurchaseRequestDto>> approve(
            @PathVariable UUID id, @RequestBody ApprovePurchaseRequestCommand cmd) {
        cmd.setRequestId(id);
        UUID updatedId = approveHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetPurchaseRequestByIdQuery(updatedId))));
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<ApiResponse<PurchaseRequestDto>> reject(
            @PathVariable UUID id, @RequestBody RejectPurchaseRequestCommand cmd) {
        cmd.setRequestId(id);
        UUID updatedId = rejectHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetPurchaseRequestByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PurchaseRequestDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(
                getByIdHandler.handle(new GetPurchaseRequestByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<PurchaseRequestDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) EnumPRStatus status,
            @RequestParam(required = false) EnumPRType type,
            @RequestParam(required = false) UUID enterpriseId,
            @RequestParam(required = false) UUID inventoryId) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListPurchaseRequestsQuery(page, size, search, status, type, enterpriseId, inventoryId))));
    }
}
