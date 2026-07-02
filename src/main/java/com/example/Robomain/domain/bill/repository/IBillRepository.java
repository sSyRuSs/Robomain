package com.example.Robomain.domain.bill.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.bill.model.Bill;

public interface IBillRepository {
    Optional<Bill> findById(UUID id);
    List<Bill> findByTaskId(UUID taskId, int page, int size);
    long countByTaskId(UUID taskId);
    Bill save(Bill bill);
    void deleteById(UUID id);
}
