package com.example.Robomain.infrastructure.persistence.entity;

import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "maintenance")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class MaintenanceJpaEntity extends BaseJpaEntity {

    @Column(name = "maintenance_name")
    private String maintenanceName;
}
