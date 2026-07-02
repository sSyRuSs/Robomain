package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.purchase_request.model.PurchaseRequest;
import com.example.Robomain.domain.purchase_request.repository.IPurchaseRequestRepository;
import com.example.Robomain.domain.shared.enums.EnumPRStatus;
import com.example.Robomain.domain.shared.enums.EnumPRType;
import com.example.Robomain.infrastructure.persistence.jpa.PurchaseRequestJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.PurchaseRequestMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PurchaseRequestRepositoryImpl implements IPurchaseRequestRepository {

    private final PurchaseRequestJpaRepository jpaRepository;
    private final PurchaseRequestMapper mapper;

    @Override public Optional<PurchaseRequest> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override public boolean existsByRequestCode(String code) {
        return jpaRepository.existsByRequestCode(code);
    }

    @Override
    public List<PurchaseRequest> search(String keyword, EnumPRStatus status, EnumPRType type,
                                         UUID enterpriseId, UUID inventoryId, int page, int size) {
        return jpaRepository.search(keyword, status, type, enterpriseId, inventoryId,
                        PageRequest.of(page, size))
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public long count(String keyword, EnumPRStatus status, EnumPRType type,
                      UUID enterpriseId, UUID inventoryId) {
        return jpaRepository.countSearch(keyword, status, type, enterpriseId, inventoryId);
    }

    @Override public PurchaseRequest save(PurchaseRequest pr) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(pr)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
