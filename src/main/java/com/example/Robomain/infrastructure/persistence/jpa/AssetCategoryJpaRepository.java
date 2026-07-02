package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.AssetCategoryJpaEntity;

@Repository
public interface AssetCategoryJpaRepository extends JpaRepository<AssetCategoryJpaEntity, UUID> {

    boolean existsByAssetCatName(String name);

    @Query("SELECT c FROM AssetCategoryJpaEntity c WHERE " +
           "(:keyword IS NULL OR LOWER(c.assetCatName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:parentId IS NULL OR c.parentCategoryId = :parentId)")
    List<AssetCategoryJpaEntity> search(
            @Param("keyword") String keyword,
            @Param("parentId") UUID parentId,
            Pageable pageable);

    @Query("SELECT COUNT(c) FROM AssetCategoryJpaEntity c WHERE " +
           "(:keyword IS NULL OR LOWER(c.assetCatName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    long countSearch(@Param("keyword") String keyword);
}
