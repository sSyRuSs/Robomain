package com.example.Robomain.application.bill.command;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateBillCommand {
    @NotBlank private String billId;
    private BigDecimal billTotal;
    private UUID taskId;
}
