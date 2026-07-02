package com.example.Robomain.application.department.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.department.dto.DepartmentDto;
import com.example.Robomain.application.department.mapper.DepartmentDtoMapper;
import com.example.Robomain.application.department.query.ListDepartmentsQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.department.repository.IDepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListDepartmentsQueryHandler {

    private final IDepartmentRepository departmentRepository;
    private final DepartmentDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<DepartmentDto> handle(ListDepartmentsQuery query) {
        var list = departmentRepository.findAll(query.getSearch(), query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = departmentRepository.count(query.getSearch());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

