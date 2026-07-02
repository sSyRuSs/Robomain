package com.example.Robomain.domain.inventory_category.model;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * InventoryCategory domain entity — hierarchical taxonomy for spare parts / consumables.
 * Scoped per enterprise. Self-referencing via parentCategoryId.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCategory {

    private UUID id;
    private String categoryCode;
    private String categoryName;
    private String description;
    private UUID parentCategoryId;
    private UUID enterpriseId;
    private Date createdAt;
    private Date updatedAt;

    public static InventoryCategory create(String code, String name, String description, UUID enterpriseId) {
        if (code == null || code.isBlank()) throw new ValidationException("Category code cannot be blank");
        if (name == null || name.isBlank()) throw new ValidationException("Category name cannot be blank");
        if (enterpriseId == null) throw new ValidationException("InventoryCategory must belong to an enterprise");
        return InventoryCategory.builder()
                .id(UUID.randomUUID())
                .categoryCode(code).categoryName(name).description(description)
                .enterpriseId(enterpriseId).build();
    }

    public void update(String name, String description) {
        if (name != null && !name.isBlank()) this.categoryName = name;
        if (description != null) this.description = description;
    }

    public void setParent(UUID parentId) {
        if (parentId != null && parentId.equals(this.id)) {
            throw new ValidationException("InventoryCategory cannot be its own parent");
        }
        this.parentCategoryId = parentId;
    }

    public Optional<UUID> getParentCategoryId() {
        return Optional.ofNullable(parentCategoryId);
    }
}
