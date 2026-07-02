package com.example.Robomain.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumInventoryStatus;
import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class InventoryJpaEntity extends BaseJpaEntity {

    @Column(name = "item_code", nullable = false, length = 50)
    private String itemCode;

    @Column(nullable = false)
    private String itemName;

    @Column(length = 1000)
    private String description;

    @Column(name = "category_id")
    private UUID categoryId;

    @Builder.Default
    @Column(nullable = false)
    private Integer quantity = 0;

    @Column(nullable = false)
    private Integer minStockLevel;

    @Column(nullable = false)
    private Integer reorderPoint;

    @Column(nullable = false)
    private Integer reorderQuantity;

    @Column(name = "unit_cost", precision = 19, scale = 2)
    private BigDecimal unitCost;

    @Column(name = "unit_of_measure", length = 50)
    private String unit;

    @Column(name = "warehouse_id")
    private UUID warehouseId;

    @Column(name = "location", length = 255)
    private String binLocation;

    @Column(name = "preferred_supplier_id")
    private UUID preferredSupplierId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumInventoryStatus status;

    @Column(name = "enterprise_id", nullable = false)
    private UUID enterpriseId;
}
