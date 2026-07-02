package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.FacilityJpaEntity;

@Repository
public interface FacilityJpaRepository extends JpaRepository<FacilityJpaEntity, UUID> {

    boolean existsByFacilityCode(String code);

    @Query("SELECT f FROM FacilityJpaEntity f WHERE " +
           "(:keyword IS NULL OR LOWER(f.facilityName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:enterpriseId IS NULL OR f.enterpriseId = :enterpriseId) AND " +
           "(:parentId IS NULL OR f.parentFacilityId = :parentId)")
    List<FacilityJpaEntity> search(
            @Param("keyword") String keyword,
            @Param("enterpriseId") UUID enterpriseId,
            @Param("parentId") UUID parentId,
            Pageable pageable);

    @Query("SELECT COUNT(f) FROM FacilityJpaEntity f WHERE " +
           "(:keyword IS NULL OR LOWER(f.facilityName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:enterpriseId IS NULL OR f.enterpriseId = :enterpriseId)")
    long countSearch(@Param("keyword") String keyword, @Param("enterpriseId") UUID enterpriseId);
}
