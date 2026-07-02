package com.example.Robomain.application.facility.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetFacilityByIdQuery {
    private final UUID facilityId;
}
