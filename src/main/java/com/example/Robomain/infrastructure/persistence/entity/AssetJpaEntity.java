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
@Table(name = "asset")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class AssetJpaEntity extends BaseJpaEntity {

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "asset_description")
    private String assetDescription;

    @Column(name = "asset_code")
    private String assetCode;

    @Column(name = "asset_quantity")
    private Integer assetQuantity;

    @Column(name = "facility_id")
    private UUID facilityId;

    @Column(name = "asset_category_id")
    private UUID assetCategoryId;

    @Column(name = "m_asset_id")
    private UUID parentAssetId;
}
