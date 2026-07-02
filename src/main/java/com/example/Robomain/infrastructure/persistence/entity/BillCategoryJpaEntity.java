package com.example.Robomain.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bill_category")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class BillCategoryJpaEntity extends BaseJpaEntity {

    @Column(name = "bill_category_name", nullable = false)
    private String billCategoryName;

    @Column(name = "bill_category_price", precision = 19, scale = 2)
    private BigDecimal billCategoryPrice;

    @Column(name = "bill_id")
    private UUID billId;
}
