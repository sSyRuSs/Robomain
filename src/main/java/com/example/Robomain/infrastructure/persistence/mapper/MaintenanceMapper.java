package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.maintenance.model.Maintenance;
import com.example.Robomain.infrastructure.persistence.entity.MaintenanceJpaEntity;

@Component
public class MaintenanceMapper {

    public Maintenance toDomain(MaintenanceJpaEntity e) {
        if (e == null) return null;
        return Maintenance.builder()
                .id(e.getId()).maintenanceName(e.getMaintenanceName())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public MaintenanceJpaEntity toJpa(Maintenance d) {
        if (d == null) return null;
        MaintenanceJpaEntity e = MaintenanceJpaEntity.builder()
                .maintenanceName(d.getMaintenanceName()).build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
