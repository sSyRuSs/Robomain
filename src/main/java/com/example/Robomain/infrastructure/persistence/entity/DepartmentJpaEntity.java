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
@Table(name = "department")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class DepartmentJpaEntity extends BaseJpaEntity {

    @Column(name = "department_name", nullable = false)
    private String departmentName;
}
