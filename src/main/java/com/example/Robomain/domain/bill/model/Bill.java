package com.example.Robomain.domain.bill.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Bill domain entity — a financial invoice associated with a Task.
 * Contains a reference billId (human-readable) and total amount.
 * Line items are stored separately as BillCategory records.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    private UUID id;
    private String billId;        // human-readable reference, e.g. "BILL-2025-001"
    private BigDecimal billTotal;
    private UUID taskId;
    private Date createdAt;
    private Date updatedAt;

    public static Bill create(String billId, BigDecimal billTotal, UUID taskId) {
        if (billId == null || billId.isBlank()) throw new ValidationException("Bill ID cannot be blank");
        return Bill.builder()
                .id(UUID.randomUUID())
                .billId(billId).billTotal(billTotal != null ? billTotal : BigDecimal.ZERO)
                .taskId(taskId)
                .build();
    }

    public void update(String billId, BigDecimal billTotal) {
        if (billId != null && !billId.isBlank()) this.billId = billId;
        if (billTotal != null) this.billTotal = billTotal;
    }
}
