package com.example.Robomain.application.bill.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class BillDto {
    private UUID id;
    private String billId;
    private BigDecimal billTotal;
    private UUID taskId;
}
