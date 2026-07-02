package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.facility.model.Facility;
import com.example.Robomain.domain.facility.repository.IFacilityRepository;
import com.example.Robomain.infrastructure.persistence.jpa.FacilityJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.FacilityMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FacilityRepositoryImpl implements IFacilityRepository {

    private final FacilityJpaRepository jpaRepository;
    private final FacilityMapper mapper;

    @Override
    public Optional<Facility> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByFacilityCode(String code) {
        return jpaRepository.existsByFacilityCode(code);
    }

    @Override
    public List<Facility> search(String keyword, UUID enterpriseId, UUID parentFacilityId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return jpaRepository.search(keyword, enterpriseId, parentFacilityId, pageable)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public long count(String keyword, UUID enterpriseId) {
        return jpaRepository.countSearch(keyword, enterpriseId);
    }

    @Override
    public Facility save(Facility facility) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(facility)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
