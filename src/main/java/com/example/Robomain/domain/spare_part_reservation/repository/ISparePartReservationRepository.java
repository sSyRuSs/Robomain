package com.example.Robomain.domain.spare_part_reservation.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumSparePartReservationStatus;
import com.example.Robomain.domain.spare_part_reservation.model.SparePartReservation;

public interface ISparePartReservationRepository {
    Optional<SparePartReservation> findById(UUID id);
    List<SparePartReservation> search(UUID inventoryId, UUID workOrderId,
                                       EnumSparePartReservationStatus status, UUID enterpriseId,
                                       int page, int size);
    long count(UUID inventoryId, UUID workOrderId, EnumSparePartReservationStatus status, UUID enterpriseId);
    SparePartReservation save(SparePartReservation reservation);
    void deleteById(UUID id);
}
