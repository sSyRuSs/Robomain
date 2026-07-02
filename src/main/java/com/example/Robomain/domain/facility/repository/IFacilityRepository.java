package com.example.Robomain.domain.facility.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.facility.model.Facility;

public interface IFacilityRepository {

    Optional<Facility> findById(UUID id);

    boolean existsByFacilityCode(String code);

    List<Facility> search(String keyword, UUID enterpriseId, UUID parentFacilityId, int page, int size);

    long count(String keyword, UUID enterpriseId);

    Facility save(Facility facility);

    void deleteById(UUID id);
}
