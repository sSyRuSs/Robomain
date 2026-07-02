package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.facility.model.Facility;
import com.example.Robomain.infrastructure.persistence.entity.FacilityJpaEntity;

@Component
public class FacilityMapper {

    public Facility toDomain(FacilityJpaEntity e) {
        if (e == null) return null;
        return Facility.builder()
                .id(e.getId()).facilityName(e.getFacilityName()).facilityCode(e.getFacilityCode())
                .facilityAddress(e.getFacilityAddress()).facilityPhone(e.getFacilityPhone())
                .totalSubFacility(e.getTotalSubFacility()).enterpriseId(e.getEnterpriseId())
                .parentFacilityId(e.getParentFacilityId()).assetCategoryId(e.getAssetCategoryId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public FacilityJpaEntity toJpa(Facility d) {
        if (d == null) return null;
        FacilityJpaEntity e = FacilityJpaEntity.builder()
                .facilityName(d.getFacilityName()).facilityCode(d.getFacilityCode())
                .facilityAddress(d.getFacilityAddress()).facilityPhone(d.getFacilityPhone())
                .totalSubFacility(d.getTotalSubFacility()).enterpriseId(d.getEnterpriseId())
                .parentFacilityId(d.getParentFacilityId().orElse(null))
                .assetCategoryId(d.getAssetCategoryId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
