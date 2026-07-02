package com.example.Robomain.application.warehouse.dto;

import java.util.UUID;
import com.example.Robomain.domain.shared.enums.EnumWarehouseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class WarehouseDto {
    private UUID id;
    private String warehouseCode;
    private String warehouseName;
    private String address;
    private UUID facilityId;
    private String managerName;
    private String contactPhone;
    private String contactEmail;
    private EnumWarehouseType type;
    private UUID enterpriseId;
}
