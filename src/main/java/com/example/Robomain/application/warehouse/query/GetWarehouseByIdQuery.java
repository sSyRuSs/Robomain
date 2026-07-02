package com.example.Robomain.application.warehouse.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetWarehouseByIdQuery {
    private final UUID warehouseId;
}
