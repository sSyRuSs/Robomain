package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.bill_category.command.CreateBillCategoryCommand;
import com.example.Robomain.application.bill_category.command.UpdateBillCategoryCommand;
import com.example.Robomain.application.bill_category.dto.BillCategoryDto;
import com.example.Robomain.application.bill_category.handler.*;
import com.example.Robomain.application.bill_category.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.BILL_CATEGORY_PATH)
@RequiredArgsConstructor
public class BillCategoryController {

    private final CreateBillCategoryCommandHandler createHandler;
    private final UpdateBillCategoryCommandHandler updateHandler;
    private final GetBillCategoryByIdQueryHandler getByIdHandler;
    private final ListBillCategoriesQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<BillCategoryDto>> create(
            @Valid @RequestBody CreateBillCategoryCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetBillCategoryByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BillCategoryDto>> update(
            @PathVariable UUID id, @RequestBody UpdateBillCategoryCommand cmd) {
        cmd.setId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetBillCategoryByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BillCategoryDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(
                getByIdHandler.handle(new GetBillCategoryByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<BillCategoryDto>>> listByBill(
            @RequestParam UUID billId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(
                listHandler.handle(new ListBillCategoriesQuery(billId, page, size))));
    }
}
