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
@Table(name = "asset_status")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class AssetStatusJpaEntity extends BaseJpaEntity {

    @Column(name = "asset_id")
    private UUID assetId;

    @Column(name = "status_enum")
    @Enumerated(EnumType.STRING)
    private EnumAssetStatus status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "from_date")
    private Date fromDate;
}
