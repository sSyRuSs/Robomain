package com.example.Robomain.application.facility.command;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateFacilityCommand {
    @NotBlank(message = "Facility name is required")
    private String facilityName;
    private String facilityCode;
    private String facilityAddress;
    private String facilityPhone;
    @NotNull(message = "Enterprise ID is required")
    private UUID enterpriseId;
    private UUID parentFacilityId;
    private UUID assetCategoryId;
}
