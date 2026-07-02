package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.BillCategoryJpaEntity;

@Repository
public interface BillCategoryJpaRepository extends JpaRepository<BillCategoryJpaEntity, UUID> {

    @Query("SELECT bc FROM BillCategoryJpaEntity bc WHERE bc.billId = :billId ORDER BY bc.createdAt DESC")
    List<BillCategoryJpaEntity> findByBillId(@Param("billId") UUID billId, Pageable pageable);

    @Query("SELECT COUNT(bc) FROM BillCategoryJpaEntity bc WHERE bc.billId = :billId")
    long countByBillId(@Param("billId") UUID billId);
}
