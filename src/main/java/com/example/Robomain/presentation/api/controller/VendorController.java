package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.vendor.command.CreateVendorCommand;
import com.example.Robomain.application.vendor.command.UpdateVendorCommand;
import com.example.Robomain.application.vendor.dto.VendorDto;
import com.example.Robomain.application.vendor.handler.*;
import com.example.Robomain.application.vendor.query.*;
import com.example.Robomain.domain.shared.enums.EnumVendorStatus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.VENDOR_PATH)
@RequiredArgsConstructor
public class VendorController {

    private final CreateVendorCommandHandler createHandler;
    private final UpdateVendorCommandHandler updateHandler;
    private final GetVendorByIdQueryHandler getByIdHandler;
    private final ListVendorsQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<VendorDto>> create(@Valid @RequestBody CreateVendorCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetVendorByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VendorDto>> update(
            @PathVariable UUID id, @RequestBody UpdateVendorCommand cmd) {
        cmd.setVendorId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetVendorByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VendorDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetVendorByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<VendorDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UUID enterpriseId,
            @RequestParam(required = false) EnumVendorStatus status) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListVendorsQuery(page, size, search, enterpriseId, status))));
    }
}
