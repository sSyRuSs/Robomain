package com.example.Robomain.domain.asset_status.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumAssetStatus;
import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * AssetStatus domain entity — the current operational status of an asset.
 * One-to-one with Asset. Changes are logged in AssetStatusHistory.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetStatus {

    private UUID id;
    private UUID assetId;
    private EnumAssetStatus status;
    private String reason;
    private Date fromDate;
    private Date createdAt;
    private Date updatedAt;

    public static AssetStatus create(UUID assetId, EnumAssetStatus status, String reason, Date fromDate) {
        if (assetId == null) throw new ValidationException("AssetStatus must reference an asset");
        if (status == null) throw new ValidationException("Status cannot be null");
        return AssetStatus.builder()
                .id(UUID.randomUUID())
                .assetId(assetId)
                .status(status)
                .reason(reason)
                .fromDate(fromDate != null ? fromDate : new Date())
                .build();
    }

    public void update(EnumAssetStatus status, String reason, Date fromDate) {
        if (status != null) this.status = status;
        if (reason != null) this.reason = reason;
        if (fromDate != null) this.fromDate = fromDate;
    }
}
