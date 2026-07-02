package com.example.Robomain.application.purchase_request.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPRStatus;
import com.example.Robomain.domain.shared.enums.EnumPRType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class PurchaseRequestDto {
    private UUID id;
    private String requestCode;
    private UUID inventoryId;
    private int requestedQuantity;
    private BigDecimal estimatedCost;
    private EnumPRStatus status;
    private EnumPRType type;
    private String reason;
    private UUID requestedBy;
    private LocalDate requestedDate;
    private LocalDate requiredDate;
    private UUID approvedBy;
    private LocalDate approvedDate;
    private String approvalNotes;
    private UUID vendorId;
    private UUID enterpriseId;
}
