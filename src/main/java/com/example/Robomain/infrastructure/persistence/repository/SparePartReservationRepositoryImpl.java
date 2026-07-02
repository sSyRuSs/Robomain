package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumSparePartReservationStatus;
import com.example.Robomain.domain.spare_part_reservation.model.SparePartReservation;
import com.example.Robomain.domain.spare_part_reservation.repository.ISparePartReservationRepository;
import com.example.Robomain.infrastructure.persistence.jpa.SparePartReservationJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.SparePartReservationMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SparePartReservationRepositoryImpl implements ISparePartReservationRepository {

    private final SparePartReservationJpaRepository jpaRepository;
    private final SparePartReservationMapper mapper;

    @Override public Optional<SparePartReservation> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<SparePartReservation> search(UUID inventoryId, UUID workOrderId,
                                              EnumSparePartReservationStatus status, UUID enterpriseId,
                                              int page, int size) {
        return jpaRepository.search(inventoryId, workOrderId, status, enterpriseId, PageRequest.of(page, size))
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public long count(UUID inventoryId, UUID workOrderId, EnumSparePartReservationStatus status, UUID enterpriseId) {
        return jpaRepository.countSearch(inventoryId, workOrderId, status, enterpriseId);
    }

    @Override public SparePartReservation save(SparePartReservation r) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(r)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
