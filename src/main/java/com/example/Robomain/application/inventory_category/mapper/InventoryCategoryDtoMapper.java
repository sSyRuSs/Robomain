package com.example.Robomain.application.inventory_category.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.inventory_category.dto.InventoryCategoryDto;
import com.example.Robomain.domain.inventory_category.model.InventoryCategory;

@Component
public class InventoryCategoryDtoMapper {

    public InventoryCategoryDto toDto(InventoryCategory c) {
        return InventoryCategoryDto.builder()
                .id(c.getId()).categoryCode(c.getCategoryCode()).categoryName(c.getCategoryName())
                .description(c.getDescription())
                .parentCategoryId(c.getParentCategoryId().orElse(null))
                .enterpriseId(c.getEnterpriseId())
                .build();
    }
}
