package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.inventory_category.model.InventoryCategory;
import com.example.Robomain.infrastructure.persistence.entity.InventoryCategoryJpaEntity;

@Component
public class InventoryCategoryMapper {

    public InventoryCategory toDomain(InventoryCategoryJpaEntity e) {
        if (e == null) return null;
        return InventoryCategory.builder()
                .id(e.getId()).categoryCode(e.getCategoryCode()).categoryName(e.getCategoryName())
                .description(e.getDescription()).parentCategoryId(e.getParentCategoryId())
                .enterpriseId(e.getEnterpriseId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public InventoryCategoryJpaEntity toJpa(InventoryCategory d) {
        if (d == null) return null;
        InventoryCategoryJpaEntity e = InventoryCategoryJpaEntity.builder()
                .categoryCode(d.getCategoryCode()).categoryName(d.getCategoryName())
                .description(d.getDescription())
                .parentCategoryId(d.getParentCategoryId().orElse(null))
                .enterpriseId(d.getEnterpriseId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
