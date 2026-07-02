package com.example.Robomain.domain.enterprise.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.enterprise.model.Enterprise;

public interface IEnterpriseRepository {

    Optional<Enterprise> findById(UUID id);

    boolean existsByEnterpriseCode(String code);

    List<Enterprise> search(String keyword, int page, int size);

    long count(String keyword);

    Enterprise save(Enterprise enterprise);

    void deleteById(UUID id);
}
