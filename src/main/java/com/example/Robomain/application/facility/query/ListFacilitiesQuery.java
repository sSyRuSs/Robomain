package com.example.Robomain.application.facility.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListFacilitiesQuery {
    private int page = 0;
    private int size = 10;
    private String search;
    private UUID enterpriseId;
    private UUID parentFacilityId;
}
