package com.example.Robomain.application.maintenance.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListMaintenancesQuery {
    private int page = 0;
    private int size = 10;
    private String search;
}
