package com.example.Robomain.domain.bill_category.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * BillCategory domain entity — a line item within a Bill.
 * Represents a specific cost category (labour, parts, travel, etc.)
 * and its price contribution to the bill total.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillCategory {

    private UUID id;
    private String billCategoryName;
    private BigDecimal billCategoryPrice;
    private UUID billId;
    private Date createdAt;
    private Date updatedAt;

    public static BillCategory create(String name, BigDecimal price, UUID billId) {
        if (name == null || name.isBlank()) throw new ValidationException("Bill category name cannot be blank");
        if (billId == null) throw new ValidationException("BillCategory must belong to a bill");
        return BillCategory.builder()
                .id(UUID.randomUUID())
                .billCategoryName(name)
                .billCategoryPrice(price != null ? price : BigDecimal.ZERO)
                .billId(billId)
                .build();
    }

    public void update(String name, BigDecimal price) {
        if (name != null && !name.isBlank()) this.billCategoryName = name;
        if (price != null) this.billCategoryPrice = price;
    }
}
