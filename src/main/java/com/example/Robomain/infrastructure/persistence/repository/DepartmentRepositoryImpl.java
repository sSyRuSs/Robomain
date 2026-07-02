package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.department.model.Department;
import com.example.Robomain.domain.department.repository.IDepartmentRepository;
import com.example.Robomain.infrastructure.persistence.jpa.DepartmentJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.DepartmentMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements IDepartmentRepository {

    private final DepartmentJpaRepository jpaRepository;
    private final DepartmentMapper mapper;

    @Override
    public Optional<Department> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByDepartmentName(String name) {
        return jpaRepository.existsByDepartmentName(name);
    }

    @Override
    public List<Department> findAll(String search, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return jpaRepository.search(search, pageable).stream().map(mapper::toDomain).toList();
    }

    @Override
    public long count(String search) {
        return jpaRepository.countSearch(search);
    }

    @Override
    public Department save(Department department) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(department)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
