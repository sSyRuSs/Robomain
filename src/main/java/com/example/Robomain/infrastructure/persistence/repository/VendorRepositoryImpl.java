package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumVendorStatus;
import com.example.Robomain.domain.vendor.model.Vendor;
import com.example.Robomain.domain.vendor.repository.IVendorRepository;
import com.example.Robomain.infrastructure.persistence.jpa.VendorJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.VendorMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VendorRepositoryImpl implements IVendorRepository {

    private final VendorJpaRepository jpaRepository;
    private final VendorMapper mapper;

    @Override public Optional<Vendor> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override public boolean existsByVendorCode(String code) {
        return jpaRepository.existsByVendorCode(code);
    }

    @Override
    public List<Vendor> search(String keyword, EnumVendorStatus status, UUID enterpriseId, int page, int size) {
        return jpaRepository.search(keyword, status, enterpriseId,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long count(String keyword, UUID enterpriseId) {
        return jpaRepository.countSearch(keyword, enterpriseId);
    }

    @Override public Vendor save(Vendor v) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(v)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
