package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.warehouse.model.Warehouse;
import com.example.Robomain.infrastructure.persistence.entity.WarehouseJpaEntity;

@Component
public class WarehouseMapper {

    public Warehouse toDomain(WarehouseJpaEntity e) {
        if (e == null) return null;
        return Warehouse.builder()
                .id(e.getId()).warehouseCode(e.getWarehouseCode()).warehouseName(e.getWarehouseName())
                .address(e.getAddress()).facilityId(e.getFacilityId())
                .managerName(e.getManagerName()).contactPhone(e.getContactPhone())
                .contactEmail(e.getContactEmail()).type(e.getType())
                .enterpriseId(e.getEnterpriseId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public WarehouseJpaEntity toJpa(Warehouse d) {
        if (d == null) return null;
        WarehouseJpaEntity e = WarehouseJpaEntity.builder()
                .warehouseCode(d.getWarehouseCode()).warehouseName(d.getWarehouseName())
                .address(d.getAddress()).facilityId(d.getFacilityId())
                .managerName(d.getManagerName()).contactPhone(d.getContactPhone())
                .contactEmail(d.getContactEmail()).type(d.getType())
                .enterpriseId(d.getEnterpriseId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
