package com.example.Robomain.application.bill_category.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class BillCategoryDto {
    private UUID id;
    private String billCategoryName;
    private BigDecimal billCategoryPrice;
    private UUID billId;
}
