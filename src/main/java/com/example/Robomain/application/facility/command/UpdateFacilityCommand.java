package com.example.Robomain.application.facility.command;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateFacilityCommand {
    private UUID facilityId;
    private String facilityName;
    private String facilityAddress;
    private String facilityPhone;
}
