package com.example.Robomain.application.spare_part_reservation.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.spare_part_reservation.command.CancelSparePartReservationCommand;
import com.example.Robomain.application.spare_part_reservation.mapper.SparePartReservationDtoMapper;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.spare_part_reservation.repository.ISparePartReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CancelSparePartReservationCommandHandler {

    private final ISparePartReservationRepository reservationRepository;
    private final SparePartReservationDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CancelSparePartReservationCommand cmd) {
        var reservation = reservationRepository.findById(cmd.getReservationId())
                .orElseThrow(() -> new ResourceNotFoundException("SparePartReservation", cmd.getReservationId()));
        reservation.cancel();
        return reservationRepository.save(reservation).getId();
    }
}
