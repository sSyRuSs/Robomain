package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.InventoryCategoryJpaEntity;

@Repository
public interface InventoryCategoryJpaRepository extends JpaRepository<InventoryCategoryJpaEntity, UUID> {

    boolean existsByCategoryCode(String code);

    @Query("SELECT c FROM InventoryCategoryJpaEntity c WHERE " +
           "(:keyword IS NULL OR LOWER(c.categoryName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:enterpriseId IS NULL OR c.enterpriseId = :enterpriseId) AND " +
           "(:parentId IS NULL OR c.parentCategoryId = :parentId)")
    List<InventoryCategoryJpaEntity> search(
            @Param("keyword") String keyword,
            @Param("enterpriseId") UUID enterpriseId,
            @Param("parentId") UUID parentId,
            Pageable pageable);

    @Query("SELECT COUNT(c) FROM InventoryCategoryJpaEntity c WHERE " +
           "(:keyword IS NULL OR LOWER(c.categoryName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:enterpriseId IS NULL OR c.enterpriseId = :enterpriseId)")
    long countSearch(@Param("keyword") String keyword, @Param("enterpriseId") UUID enterpriseId);
}
