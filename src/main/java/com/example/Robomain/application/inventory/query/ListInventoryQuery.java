package com.example.Robomain.application.inventory.query;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumInventoryStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListInventoryQuery {
    private int page = 0;
    private int size = 10;
    private String search;
    private UUID categoryId;
    private UUID warehouseId;
    private EnumInventoryStatus status;
    private UUID enterpriseId;
}
