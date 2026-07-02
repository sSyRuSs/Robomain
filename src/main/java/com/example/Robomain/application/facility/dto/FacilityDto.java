package com.example.Robomain.application.facility.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class FacilityDto {
    private UUID id;
    private String facilityName;
    private String facilityCode;
    private String facilityAddress;
    private String facilityPhone;
    private Integer totalSubFacility;
    private UUID enterpriseId;
    private UUID parentFacilityId;
    private UUID assetCategoryId;
}
