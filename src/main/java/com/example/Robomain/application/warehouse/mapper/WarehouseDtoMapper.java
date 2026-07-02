package com.example.Robomain.application.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.warehouse.dto.WarehouseDto;
import com.example.Robomain.domain.warehouse.model.Warehouse;

@Component
public class WarehouseDtoMapper {

    public WarehouseDto toDto(Warehouse w) {
        return WarehouseDto.builder()
                .id(w.getId()).warehouseCode(w.getWarehouseCode()).warehouseName(w.getWarehouseName())
                .address(w.getAddress()).facilityId(w.getFacilityId())
                .managerName(w.getManagerName()).contactPhone(w.getContactPhone())
                .contactEmail(w.getContactEmail()).type(w.getType())
                .enterpriseId(w.getEnterpriseId())
                .build();
    }
}
