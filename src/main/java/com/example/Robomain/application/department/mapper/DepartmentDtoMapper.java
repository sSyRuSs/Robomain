package com.example.Robomain.application.department.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.department.dto.DepartmentDto;
import com.example.Robomain.domain.department.model.Department;

@Component
public class DepartmentDtoMapper {

    public DepartmentDto toDto(Department d) {
        return DepartmentDto.builder().id(d.getId()).departmentName(d.getDepartmentName()).build();
    }
}
