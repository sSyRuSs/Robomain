package com.example.Robomain.application.spare_part_reservation.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetSparePartReservationByIdQuery {
    private final UUID reservationId;
}
