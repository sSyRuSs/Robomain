package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.facility.command.CreateFacilityCommand;
import com.example.Robomain.application.facility.command.UpdateFacilityCommand;
import com.example.Robomain.application.facility.dto.FacilityDto;
import com.example.Robomain.application.facility.handler.*;
import com.example.Robomain.application.facility.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.FACILITY_PATH)
@RequiredArgsConstructor
public class FacilityController {

    private final CreateFacilityCommandHandler createHandler;
    private final UpdateFacilityCommandHandler updateHandler;
    private final GetFacilityByIdQueryHandler getByIdHandler;
    private final ListFacilitiesQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<FacilityDto>> create(@Valid @RequestBody CreateFacilityCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetFacilityByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FacilityDto>> update(
            @PathVariable UUID id, @RequestBody UpdateFacilityCommand cmd) {
        cmd.setFacilityId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetFacilityByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FacilityDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetFacilityByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<FacilityDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UUID enterpriseId,
            @RequestParam(required = false) UUID parentFacilityId) {
        return ResponseEntity.ok(ApiResponse.success(
                listHandler.handle(new ListFacilitiesQuery(page, size, search, enterpriseId, parentFacilityId))));
    }
}
