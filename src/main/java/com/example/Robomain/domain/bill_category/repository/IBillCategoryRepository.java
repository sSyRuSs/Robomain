package com.example.Robomain.domain.bill_category.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.bill_category.model.BillCategory;

public interface IBillCategoryRepository {
    Optional<BillCategory> findById(UUID id);
    List<BillCategory> findByBillId(UUID billId, int page, int size);
    long countByBillId(UUID billId);
    BillCategory save(BillCategory billCategory);
    void deleteById(UUID id);
}
