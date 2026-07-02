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
@Table(name = "facility")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class FacilityJpaEntity extends BaseJpaEntity {

    @Column(name = "facility_name", nullable = false)
    private String facilityName;

    @Column(name = "facility_code")
    private String facilityCode;

    @Column(name = "facility_address")
    private String facilityAddress;

    @Column(name = "facility_phone")
    private String facilityPhone;

    @Column(name = "total_sub_facility")
    private Integer totalSubFacility;

    @Column(name = "enterprise_id")
    private UUID enterpriseId;

    @Column(name = "m_facility_id")
    private UUID parentFacilityId;

    @Column(name = "asset_category_id")
    private UUID assetCategoryId;
}
