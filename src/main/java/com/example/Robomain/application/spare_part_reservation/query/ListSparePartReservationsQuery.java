package com.example.Robomain.application.spare_part_reservation.query;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumSparePartReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ListSparePartReservationsQuery {
    private final int page;
    private final int size;
    private final UUID inventoryId;
    private final UUID workOrderId;
    private final EnumSparePartReservationStatus status;
    private final UUID enterpriseId;
}
