package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.DepartmentJpaEntity;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<DepartmentJpaEntity, UUID> {

    boolean existsByDepartmentName(String name);

    @Query("SELECT d FROM DepartmentJpaEntity d WHERE " +
           "(:keyword IS NULL OR LOWER(d.departmentName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<DepartmentJpaEntity> search(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT COUNT(d) FROM DepartmentJpaEntity d WHERE " +
           "(:keyword IS NULL OR LOWER(d.departmentName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    long countSearch(@Param("keyword") String keyword);
}
