package com.example.Robomain.application.asset.query;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListAssetsQuery {
    private int page = 0;
    private int size = 10;
    private String search;
    private UUID facilityId;
    private UUID categoryId;
}
