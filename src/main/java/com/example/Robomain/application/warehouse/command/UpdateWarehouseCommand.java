package com.example.Robomain.application.warehouse.command;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateWarehouseCommand {
    private UUID warehouseId;
    private String warehouseName;
    private String address;
    private String managerName;
    private String contactPhone;
    private String contactEmail;
}
