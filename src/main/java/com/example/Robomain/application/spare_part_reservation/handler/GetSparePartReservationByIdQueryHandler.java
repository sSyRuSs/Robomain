package com.example.Robomain.application.spare_part_reservation.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.spare_part_reservation.dto.SparePartReservationDto;
import com.example.Robomain.application.spare_part_reservation.mapper.SparePartReservationDtoMapper;
import com.example.Robomain.application.spare_part_reservation.query.GetSparePartReservationByIdQuery;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.spare_part_reservation.repository.ISparePartReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetSparePartReservationByIdQueryHandler {

    private final ISparePartReservationRepository reservationRepository;
    private final SparePartReservationDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public SparePartReservationDto handle(GetSparePartReservationByIdQuery query) {
        return reservationRepository.findById(query.getReservationId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("SparePartReservation", query.getReservationId()));
    }
}

