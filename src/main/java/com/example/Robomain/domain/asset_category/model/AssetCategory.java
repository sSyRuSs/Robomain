package com.example.Robomain.domain.asset_category.model;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * AssetCategory domain entity — hierarchical taxonomy for classifying assets.
 * Self-referencing via parentCategoryId. Pure Java, no JPA/Spring.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetCategory {

    private UUID id;
    private String assetCatName;
    private String assetCatDescription;
    private UUID parentCategoryId; // null = top-level
    private Date createdAt;
    private Date updatedAt;

    public static AssetCategory create(String name, String description) {
        if (name == null || name.isBlank()) throw new ValidationException("Asset category name cannot be blank");
        return AssetCategory.builder()
                .id(UUID.randomUUID())
                .assetCatName(name)
                .assetCatDescription(description)
                .build();
    }

    public void update(String name, String description) {
        if (name != null && !name.isBlank()) this.assetCatName = name;
        if (description != null) this.assetCatDescription = description;
    }

    public void setParent(UUID parentId) {
        if (parentId != null && parentId.equals(this.id)) {
            throw new ValidationException("AssetCategory cannot be its own parent");
        }
        this.parentCategoryId = parentId;
    }

    public Optional<UUID> getParentCategoryId() {
        return Optional.ofNullable(parentCategoryId);
    }
}
