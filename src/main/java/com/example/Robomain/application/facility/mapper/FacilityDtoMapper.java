package com.example.Robomain.application.facility.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.facility.dto.FacilityDto;
import com.example.Robomain.domain.facility.model.Facility;

@Component
public class FacilityDtoMapper {

    public FacilityDto toDto(Facility f) {
        return FacilityDto.builder()
                .id(f.getId()).facilityName(f.getFacilityName()).facilityCode(f.getFacilityCode())
                .facilityAddress(f.getFacilityAddress()).facilityPhone(f.getFacilityPhone())
                .totalSubFacility(f.getTotalSubFacility()).enterpriseId(f.getEnterpriseId())
                .parentFacilityId(f.getParentFacilityId().orElse(null))
                .assetCategoryId(f.getAssetCategoryId())
                .build();
    }
}
