package com.example.Robomain.application.bill.command;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateBillCommand {
    private UUID id;
    private String billId;
    private BigDecimal billTotal;
}
