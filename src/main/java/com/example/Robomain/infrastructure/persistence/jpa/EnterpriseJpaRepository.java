package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.EnterpriseJpaEntity;

@Repository
public interface EnterpriseJpaRepository extends JpaRepository<EnterpriseJpaEntity, UUID> {

    boolean existsByEnterpriseCode(String code);

    @Query("SELECT e FROM EnterpriseJpaEntity e WHERE " +
           "(:keyword IS NULL OR LOWER(e.enterpriseName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<EnterpriseJpaEntity> search(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT COUNT(e) FROM EnterpriseJpaEntity e WHERE " +
           "(:keyword IS NULL OR LOWER(e.enterpriseName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    long countSearch(@Param("keyword") String keyword);
}
