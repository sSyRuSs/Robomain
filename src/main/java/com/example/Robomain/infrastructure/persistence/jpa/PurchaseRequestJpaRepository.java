package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumPRStatus;
import com.example.Robomain.domain.shared.enums.EnumPRType;
import com.example.Robomain.infrastructure.persistence.entity.PurchaseRequestJpaEntity;

@Repository
public interface PurchaseRequestJpaRepository extends JpaRepository<PurchaseRequestJpaEntity, UUID> {

    boolean existsByRequestCode(String code);

    @Query("SELECT pr FROM PurchaseRequestJpaEntity pr WHERE " +
           "(:keyword IS NULL OR LOWER(pr.requestCode) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:status IS NULL OR pr.status = :status) AND " +
           "(:type IS NULL OR pr.type = :type) AND " +
           "(:enterpriseId IS NULL OR pr.enterpriseId = :enterpriseId) AND " +
           "(:inventoryId IS NULL OR pr.inventoryId = :inventoryId) " +
           "ORDER BY pr.requestedDate DESC")
    List<PurchaseRequestJpaEntity> search(
            @Param("keyword") String keyword,
            @Param("status") EnumPRStatus status,
            @Param("type") EnumPRType type,
            @Param("enterpriseId") UUID enterpriseId,
            @Param("inventoryId") UUID inventoryId,
            Pageable pageable);

    @Query("SELECT COUNT(pr) FROM PurchaseRequestJpaEntity pr WHERE " +
           "(:keyword IS NULL OR LOWER(pr.requestCode) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:status IS NULL OR pr.status = :status) AND " +
           "(:type IS NULL OR pr.type = :type) AND " +
           "(:enterpriseId IS NULL OR pr.enterpriseId = :enterpriseId) AND " +
           "(:inventoryId IS NULL OR pr.inventoryId = :inventoryId)")
    long countSearch(
            @Param("keyword") String keyword,
            @Param("status") EnumPRStatus status,
            @Param("type") EnumPRType type,
            @Param("enterpriseId") UUID enterpriseId,
            @Param("inventoryId") UUID inventoryId);
}
