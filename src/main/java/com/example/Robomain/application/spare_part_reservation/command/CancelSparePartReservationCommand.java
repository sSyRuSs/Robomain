package com.example.Robomain.application.spare_part_reservation.command;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CancelSparePartReservationCommand {
    private UUID reservationId;
}
