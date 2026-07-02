package com.example.Robomain.infrastructure.persistence.entity;

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
@Table(name = "inventory_category")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class InventoryCategoryJpaEntity extends BaseJpaEntity {

    @Column(name = "category_code", nullable = false, length = 50)
    private String categoryCode;

    @Column(nullable = false)
    private String categoryName;

    @Column(name = "category_description", length = 500)
    private String description;

    @Column(name = "parent_category_id")
    private UUID parentCategoryId;

    @Column(name = "enterprise_id", nullable = false)
    private UUID enterpriseId;
}
