package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumVendorStatus;
import com.example.Robomain.infrastructure.persistence.entity.VendorJpaEntity;

@Repository
public interface VendorJpaRepository extends JpaRepository<VendorJpaEntity, UUID> {

    boolean existsByVendorCode(String code);

    @Query("SELECT v FROM VendorJpaEntity v WHERE " +
           "(:keyword IS NULL OR LOWER(v.vendorName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:status IS NULL OR v.status = :status) AND " +
           "(:enterpriseId IS NULL OR v.enterpriseId = :enterpriseId)")
    List<VendorJpaEntity> search(@Param("keyword") String keyword,
                                  @Param("status") EnumVendorStatus status,
                                  @Param("enterpriseId") UUID enterpriseId,
                                  Pageable pageable);

    @Query("SELECT COUNT(v) FROM VendorJpaEntity v WHERE " +
           "(:keyword IS NULL OR LOWER(v.vendorName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:enterpriseId IS NULL OR v.enterpriseId = :enterpriseId)")
    long countSearch(@Param("keyword") String keyword, @Param("enterpriseId") UUID enterpriseId);
}
