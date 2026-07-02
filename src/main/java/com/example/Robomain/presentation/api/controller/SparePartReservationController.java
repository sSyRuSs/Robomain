package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.spare_part_reservation.command.*;
import com.example.Robomain.application.spare_part_reservation.dto.SparePartReservationDto;
import com.example.Robomain.application.spare_part_reservation.handler.*;
import com.example.Robomain.application.spare_part_reservation.query.*;
import com.example.Robomain.domain.shared.enums.EnumSparePartReservationStatus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.SPARE_PART_RESERVATION_PATH)
@RequiredArgsConstructor
public class SparePartReservationController {

    private final CreateSparePartReservationCommandHandler createHandler;
    private final IssueSparePartReservationCommandHandler issueHandler;
    private final CancelSparePartReservationCommandHandler cancelHandler;
    private final GetSparePartReservationByIdQueryHandler getByIdHandler;
    private final ListSparePartReservationsQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<SparePartReservationDto>> create(
            @Valid @RequestBody CreateSparePartReservationCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetSparePartReservationByIdQuery(id))));
    }

    @PatchMapping("/{id}/issue")
    public ResponseEntity<ApiResponse<SparePartReservationDto>> issue(@PathVariable UUID id) {
        var cmd = new IssueSparePartReservationCommand();
        cmd.setReservationId(id);
        UUID updatedId = issueHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetSparePartReservationByIdQuery(updatedId))));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<SparePartReservationDto>> cancel(@PathVariable UUID id) {
        var cmd = new CancelSparePartReservationCommand();
        cmd.setReservationId(id);
        UUID updatedId = cancelHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetSparePartReservationByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SparePartReservationDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(
                getByIdHandler.handle(new GetSparePartReservationByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<SparePartReservationDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) UUID inventoryId,
            @RequestParam(required = false) UUID workOrderId,
            @RequestParam(required = false) EnumSparePartReservationStatus status,
            @RequestParam(required = false) UUID enterpriseId) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListSparePartReservationsQuery(page, size, inventoryId, workOrderId, status, enterpriseId))));
    }
}
