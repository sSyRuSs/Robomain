package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.bill_category.model.BillCategory;
import com.example.Robomain.domain.bill_category.repository.IBillCategoryRepository;
import com.example.Robomain.infrastructure.persistence.jpa.BillCategoryJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.BillCategoryMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BillCategoryRepositoryImpl implements IBillCategoryRepository {

    private final BillCategoryJpaRepository jpaRepository;
    private final BillCategoryMapper mapper;

    @Override public Optional<BillCategory> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<BillCategory> findByBillId(UUID billId, int page, int size) {
        return jpaRepository.findByBillId(billId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long countByBillId(UUID billId) {
        return jpaRepository.countByBillId(billId);
    }

    @Override public BillCategory save(BillCategory bc) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(bc)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
