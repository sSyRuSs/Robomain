package com.example.Robomain.domain.asset.model;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Asset domain entity — a physical or virtual asset tracked by the CMMS.
 * Can be nested via parentAssetId (sub-components of a larger asset).
 * Pure Java, no JPA/Spring.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

    private UUID id;
    private String assetName;
    private String assetDescription;
    private String assetCode;
    @Builder.Default
    private Integer assetQuantity = 0;
    private UUID facilityId;
    private UUID assetCategoryId;
    private UUID parentAssetId; // m_asset_id — null means standalone
    private Date createdAt;
    private Date updatedAt;

    public static Asset create(String name, String code, UUID facilityId, UUID assetCategoryId) {
        if (name == null || name.isBlank()) throw new ValidationException("Asset name cannot be blank");
        return Asset.builder()
                .id(UUID.randomUUID())
                .assetName(name)
                .assetCode(code)
                .facilityId(facilityId)
                .assetCategoryId(assetCategoryId)
                .assetQuantity(0)
                .build();
    }

    public void update(String name, String description, String code, Integer quantity) {
        if (name != null && !name.isBlank()) this.assetName = name;
        if (description != null) this.assetDescription = description;
        if (code != null) this.assetCode = code;
        if (quantity != null) this.assetQuantity = quantity;
    }

    public void setParent(UUID parentId) {
        if (parentId != null && parentId.equals(this.id)) {
            throw new ValidationException("Asset cannot be its own parent");
        }
        this.parentAssetId = parentId;
    }

    public Optional<UUID> getParentAssetId() {
        return Optional.ofNullable(parentAssetId);
    }
}
