package com.example.Robomain.application.warehouse.query;

import java.util.UUID;
import com.example.Robomain.domain.shared.enums.EnumWarehouseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListWarehousesQuery {
    private int page = 0;
    private int size = 10;
    private String search;
    private UUID enterpriseId;
    private EnumWarehouseType type;
}
