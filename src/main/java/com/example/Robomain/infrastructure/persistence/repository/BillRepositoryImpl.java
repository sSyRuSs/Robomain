package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.bill.model.Bill;
import com.example.Robomain.domain.bill.repository.IBillRepository;
import com.example.Robomain.infrastructure.persistence.jpa.BillJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.BillMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BillRepositoryImpl implements IBillRepository {

    private final BillJpaRepository jpaRepository;
    private final BillMapper mapper;

    @Override public Optional<Bill> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Bill> findByTaskId(UUID taskId, int page, int size) {
        return jpaRepository.findByTaskId(taskId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long countByTaskId(UUID taskId) {
        return jpaRepository.countByTaskId(taskId);
    }

    @Override public Bill save(Bill b) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(b)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
