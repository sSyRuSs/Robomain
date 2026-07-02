package com.example.Robomain.application.warehouse.command;

import java.util.UUID;
import com.example.Robomain.domain.shared.enums.EnumWarehouseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateWarehouseCommand {
    @NotBlank(message = "Warehouse code is required")
    private String warehouseCode;
    @NotBlank(message = "Warehouse name is required")
    private String warehouseName;
    private String address;
    private UUID facilityId;
    private String managerName;
    private String contactPhone;
    private String contactEmail;
    @NotNull(message = "Warehouse type is required")
    private EnumWarehouseType type;
    @NotNull(message = "Enterprise ID is required")
    private UUID enterpriseId;
}
