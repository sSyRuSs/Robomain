package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.bill.command.CreateBillCommand;
import com.example.Robomain.application.bill.command.UpdateBillCommand;
import com.example.Robomain.application.bill.dto.BillDto;
import com.example.Robomain.application.bill.handler.*;
import com.example.Robomain.application.bill.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.BILL_PATH)
@RequiredArgsConstructor
public class BillController {

    private final CreateBillCommandHandler createHandler;
    private final UpdateBillCommandHandler updateHandler;
    private final GetBillByIdQueryHandler getByIdHandler;
    private final ListBillsByTaskQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<BillDto>> create(@Valid @RequestBody CreateBillCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetBillByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BillDto>> update(
            @PathVariable UUID id, @RequestBody UpdateBillCommand cmd) {
        cmd.setId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetBillByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BillDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetBillByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<BillDto>>> listByTask(
            @RequestParam UUID taskId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(new ListBillsByTaskQuery(taskId, page, size))));
    }
}
