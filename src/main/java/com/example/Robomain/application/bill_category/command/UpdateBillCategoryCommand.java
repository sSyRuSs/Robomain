package com.example.Robomain.application.bill_category.command;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateBillCategoryCommand {
    private UUID id;
    private String billCategoryName;
    private BigDecimal billCategoryPrice;
}
