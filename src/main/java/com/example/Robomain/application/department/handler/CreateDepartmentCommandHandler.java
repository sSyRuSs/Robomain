package com.example.Robomain.application.department.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.department.command.CreateDepartmentCommand;
import com.example.Robomain.application.department.mapper.DepartmentDtoMapper;
import com.example.Robomain.domain.department.model.Department;
import com.example.Robomain.domain.department.repository.IDepartmentRepository;
import com.example.Robomain.domain.shared.exception.ConflictException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateDepartmentCommandHandler {

    private final IDepartmentRepository departmentRepository;
    private final DepartmentDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateDepartmentCommand command) {
        if (departmentRepository.existsByDepartmentName(command.getDepartmentName())) {
            throw new ConflictException("Department", "name", command.getDepartmentName());
        }
        Department saved = departmentRepository.save(Department.create(command.getDepartmentName()));
        return saved.getId();
    }
}
