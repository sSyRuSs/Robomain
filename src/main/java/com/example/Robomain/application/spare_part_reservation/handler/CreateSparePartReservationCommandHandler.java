package com.example.Robomain.application.spare_part_reservation.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.spare_part_reservation.command.CreateSparePartReservationCommand;
import com.example.Robomain.application.spare_part_reservation.mapper.SparePartReservationDtoMapper;
import com.example.Robomain.domain.spare_part_reservation.model.SparePartReservation;
import com.example.Robomain.domain.spare_part_reservation.repository.ISparePartReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSparePartReservationCommandHandler {

    private final ISparePartReservationRepository reservationRepository;
    private final SparePartReservationDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateSparePartReservationCommand cmd) {
        SparePartReservation reservation = SparePartReservation.create(
                cmd.getInventoryId(), cmd.getWorkOrderId(), cmd.getTaskId(),
                cmd.getQuantity(), cmd.getNote(), cmd.getReservedBy(), cmd.getEnterpriseId());
        return reservationRepository.save(reservation).getId();
    }
}
