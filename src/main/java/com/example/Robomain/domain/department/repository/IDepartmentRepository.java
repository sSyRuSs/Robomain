package com.example.Robomain.domain.department.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.department.model.Department;

public interface IDepartmentRepository {

    Optional<Department> findById(UUID id);

    boolean existsByDepartmentName(String name);

    List<Department> findAll(String search, int page, int size);

    long count(String search);

    Department save(Department department);

    void deleteById(UUID id);
}
