package com.example.Robomain.domain.purchase_request.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.purchase_request.model.PurchaseRequest;
import com.example.Robomain.domain.shared.enums.EnumPRStatus;
import com.example.Robomain.domain.shared.enums.EnumPRType;

public interface IPurchaseRequestRepository {
    Optional<PurchaseRequest> findById(UUID id);
    boolean existsByRequestCode(String code);
    List<PurchaseRequest> search(String keyword, EnumPRStatus status, EnumPRType type,
                                  UUID enterpriseId, UUID inventoryId, int page, int size);
    long count(String keyword, EnumPRStatus status, EnumPRType type,
               UUID enterpriseId, UUID inventoryId);
    PurchaseRequest save(PurchaseRequest purchaseRequest);
    void deleteById(UUID id);
}
