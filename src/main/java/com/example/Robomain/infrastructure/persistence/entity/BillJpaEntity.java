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
@Table(name = "bill")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class BillJpaEntity extends BaseJpaEntity {

    @Column(name = "bill_id", nullable = false)
    private String billId;

    @Column(name = "bill_total", precision = 19, scale = 2)
    private BigDecimal billTotal;

    @Column(name = "task_id")
    private UUID taskId;
}
