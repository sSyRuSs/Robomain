package com.example.Robomain.application.bill_category.command;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateBillCategoryCommand {
    @NotBlank private String billCategoryName;
    private BigDecimal billCategoryPrice;
    @NotNull private UUID billId;
}
