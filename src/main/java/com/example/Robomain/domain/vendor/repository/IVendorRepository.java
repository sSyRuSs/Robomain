package com.example.Robomain.domain.vendor.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumVendorStatus;
import com.example.Robomain.domain.vendor.model.Vendor;

public interface IVendorRepository {
    Optional<Vendor> findById(UUID id);
    boolean existsByVendorCode(String code);
    List<Vendor> search(String keyword, EnumVendorStatus status, UUID enterpriseId, int page, int size);
    long count(String keyword, UUID enterpriseId);
    Vendor save(Vendor vendor);
    void deleteById(UUID id);
}
