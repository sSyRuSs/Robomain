package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.enterprise.model.Enterprise;
import com.example.Robomain.domain.enterprise.repository.IEnterpriseRepository;
import com.example.Robomain.infrastructure.persistence.jpa.EnterpriseJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.EnterpriseMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EnterpriseRepositoryImpl implements IEnterpriseRepository {

    private final EnterpriseJpaRepository jpaRepository;
    private final EnterpriseMapper mapper;

    @Override
    public Optional<Enterprise> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEnterpriseCode(String code) {
        return jpaRepository.existsByEnterpriseCode(code);
    }

    @Override
    public List<Enterprise> search(String keyword, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return jpaRepository.search(keyword, pageable).stream().map(mapper::toDomain).toList();
    }

    @Override
    public long count(String keyword) {
        return jpaRepository.countSearch(keyword);
    }

    @Override
    public Enterprise save(Enterprise enterprise) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(enterprise)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
