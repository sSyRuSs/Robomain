package com.example.Robomain.application.department.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.department.dto.DepartmentDto;
import com.example.Robomain.application.department.mapper.DepartmentDtoMapper;
import com.example.Robomain.application.department.query.GetDepartmentByIdQuery;
import com.example.Robomain.domain.department.repository.IDepartmentRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetDepartmentByIdQueryHandler {

    private final IDepartmentRepository departmentRepository;
    private final DepartmentDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public DepartmentDto handle(GetDepartmentByIdQuery query) {
        return departmentRepository.findById(query.getDepartmentId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Department", query.getDepartmentId()));
    }
}

