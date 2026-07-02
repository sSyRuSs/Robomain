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
@Table(name = "asset_category")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class AssetCategoryJpaEntity extends BaseJpaEntity {

    @Column(name = "asset_category_name")
    private String assetCatName;

    @Column(name = "asset_category_description")
    private String assetCatDescription;

    @Column(name = "m_asset_category_id")
    private UUID parentCategoryId;
}
