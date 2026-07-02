package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.spare_part_reservation.model.SparePartReservation;
import com.example.Robomain.infrastructure.persistence.entity.SparePartReservationJpaEntity;

@Component
public class SparePartReservationMapper {

    public SparePartReservation toDomain(SparePartReservationJpaEntity e) {
        if (e == null) return null;
        return SparePartReservation.builder()
                .id(e.getId()).inventoryId(e.getInventoryId()).workOrderId(e.getWorkOrderId())
                .taskId(e.getTaskId()).quantity(e.getQuantity()).status(e.getStatus())
                .reservedAt(e.getReservedAt()).issuedAt(e.getIssuedAt()).cancelledAt(e.getCancelledAt())
                .note(e.getNote()).reservedBy(e.getReservedBy()).enterpriseId(e.getEnterpriseId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public SparePartReservationJpaEntity toJpa(SparePartReservation d) {
        if (d == null) return null;
        SparePartReservationJpaEntity e = SparePartReservationJpaEntity.builder()
                .inventoryId(d.getInventoryId()).workOrderId(d.getWorkOrderId()).taskId(d.getTaskId())
                .quantity(d.getQuantity()).status(d.getStatus()).reservedAt(d.getReservedAt())
                .issuedAt(d.getIssuedAt()).cancelledAt(d.getCancelledAt()).note(d.getNote())
                .reservedBy(d.getReservedBy()).enterpriseId(d.getEnterpriseId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
