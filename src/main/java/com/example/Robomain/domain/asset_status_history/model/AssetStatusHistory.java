package com.example.Robomain.domain.asset_status_history.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumAssetStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * AssetStatusHistory — immutable snapshot of an asset status change.
 * Created whenever AssetStatus is set or updated.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetStatusHistory {

    private UUID id;
    private UUID assetId;
    private UUID assetStatusId;
    private EnumAssetStatus status;
    private String reason;
    private Date changedAt;
    private Date createdAt;
    private Date updatedAt;

    /** Factory — records a new history entry for the given asset status change. */
    public static AssetStatusHistory record(
            UUID assetId, UUID assetStatusId, EnumAssetStatus status, String reason) {
        return AssetStatusHistory.builder()
                .id(UUID.randomUUID())
                .assetId(assetId)
                .assetStatusId(assetStatusId)
                .status(status)
                .reason(reason)
                .changedAt(new Date())
                .build();
    }
}
