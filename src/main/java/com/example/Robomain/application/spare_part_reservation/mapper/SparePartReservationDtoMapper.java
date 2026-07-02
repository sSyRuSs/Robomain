package com.example.Robomain.application.spare_part_reservation.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.spare_part_reservation.dto.SparePartReservationDto;
import com.example.Robomain.domain.spare_part_reservation.model.SparePartReservation;

@Component
public class SparePartReservationDtoMapper {

    public SparePartReservationDto toDto(SparePartReservation r) {
        return SparePartReservationDto.builder()
                .id(r.getId()).inventoryId(r.getInventoryId()).workOrderId(r.getWorkOrderId())
                .taskId(r.getTaskId()).quantity(r.getQuantity()).status(r.getStatus())
                .reservedAt(r.getReservedAt()).issuedAt(r.getIssuedAt()).cancelledAt(r.getCancelledAt())
                .note(r.getNote()).reservedBy(r.getReservedBy()).enterpriseId(r.getEnterpriseId())
                .build();
    }
}
