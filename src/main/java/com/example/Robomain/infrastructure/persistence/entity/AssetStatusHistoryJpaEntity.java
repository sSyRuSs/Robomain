package com.example.Robomain.infrastructure.persistence.entity;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumAssetStatus;
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
@Table(name = "asset_status_history")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class AssetStatusHistoryJpaEntity extends BaseJpaEntity {

    @Column(name = "asset_id", nullable = false)
    private UUID assetId;

    @Column(name = "asset_status_id")
    private UUID assetStatusId;

    @Column(name = "status_enum", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumAssetStatus status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "changed_at", nullable = false)
    private Date changedAt;
}
