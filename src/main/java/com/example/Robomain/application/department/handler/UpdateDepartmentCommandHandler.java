package com.example.Robomain.application.department.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.department.command.UpdateDepartmentCommand;
import com.example.Robomain.application.department.mapper.DepartmentDtoMapper;
import com.example.Robomain.domain.department.repository.IDepartmentRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateDepartmentCommandHandler {

    private final IDepartmentRepository departmentRepository;
    private final DepartmentDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateDepartmentCommand command) {
        var dept = departmentRepository.findById(command.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", command.getDepartmentId()));
        dept.rename(command.getDepartmentName());
        return departmentRepository.save(dept).getId();
    }
}
