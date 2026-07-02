package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.department.model.Department;
import com.example.Robomain.infrastructure.persistence.entity.DepartmentJpaEntity;

@Component
public class DepartmentMapper {

    public Department toDomain(DepartmentJpaEntity e) {
        if (e == null) return null;
        return Department.builder()
                .id(e.getId()).departmentName(e.getDepartmentName())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public DepartmentJpaEntity toJpa(Department d) {
        if (d == null) return null;
        DepartmentJpaEntity e = DepartmentJpaEntity.builder()
                .departmentName(d.getDepartmentName()).build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
