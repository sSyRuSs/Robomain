package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.AssetJpaEntity;

@Repository
public interface AssetJpaRepository extends JpaRepository<AssetJpaEntity, UUID> {

    boolean existsByAssetCode(String code);

    @Query("SELECT a FROM AssetJpaEntity a WHERE " +
           "(:keyword IS NULL OR LOWER(a.assetName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "   OR LOWER(a.assetCode) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:facilityId IS NULL OR a.facilityId = :facilityId) AND " +
           "(:categoryId IS NULL OR a.assetCategoryId = :categoryId)")
    List<AssetJpaEntity> search(
            @Param("keyword") String keyword,
            @Param("facilityId") UUID facilityId,
            @Param("categoryId") UUID categoryId,
            Pageable pageable);

    @Query("SELECT COUNT(a) FROM AssetJpaEntity a WHERE " +
           "(:keyword IS NULL OR LOWER(a.assetName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "   OR LOWER(a.assetCode) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:facilityId IS NULL OR a.facilityId = :facilityId) AND " +
           "(:categoryId IS NULL OR a.assetCategoryId = :categoryId)")
    long countSearch(
            @Param("keyword") String keyword,
            @Param("facilityId") UUID facilityId,
            @Param("categoryId") UUID categoryId);
}
