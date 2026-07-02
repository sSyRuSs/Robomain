package com.example.Robomain.infrastructure.persistence.entity;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumWarehouseType;
import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "warehouse")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class WarehouseJpaEntity extends BaseJpaEntity {

    @Column(nullable = false)
    private String warehouseCode;

    @Column(nullable = false)
    private String warehouseName;

    @Column(name = "warehouse_address", length = 500)
    private String address;

    @Column(name = "facility_id")
    private UUID facilityId;

    private String managerName;
    private String contactPhone;
    private String contactEmail;

    @Column(name = "warehouse_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumWarehouseType type;

    @Column(name = "enterprise_id", nullable = false)
    private UUID enterpriseId;
}
