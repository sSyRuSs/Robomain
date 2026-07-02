package com.example.Robomain.application.purchase_request.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreatePurchaseRequestCommand {
    @NotBlank private String requestCode;
    @NotNull private UUID inventoryId;
    @Min(1) private int requestedQuantity;
    private BigDecimal estimatedCost;
    private String reason;
    private UUID requestedBy;
    private LocalDate requiredDate;
    private UUID vendorId;
    @NotNull private UUID enterpriseId;
}
