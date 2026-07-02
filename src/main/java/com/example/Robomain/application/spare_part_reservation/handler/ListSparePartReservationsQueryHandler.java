package com.example.Robomain.application.spare_part_reservation.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.spare_part_reservation.dto.SparePartReservationDto;
import com.example.Robomain.application.spare_part_reservation.mapper.SparePartReservationDtoMapper;
import com.example.Robomain.application.spare_part_reservation.query.ListSparePartReservationsQuery;
import com.example.Robomain.domain.spare_part_reservation.repository.ISparePartReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListSparePartReservationsQueryHandler {

    private final ISparePartReservationRepository reservationRepository;
    private final SparePartReservationDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<SparePartReservationDto> handle(ListSparePartReservationsQuery query) {
        var items = reservationRepository.search(query.getInventoryId(), query.getWorkOrderId(),
                query.getStatus(), query.getEnterpriseId(), query.getPage(), query.getSize());
        var total = reservationRepository.count(query.getInventoryId(), query.getWorkOrderId(),
                query.getStatus(), query.getEnterpriseId());
        var dtos = items.stream().map(dtoMapper::toDto).toList();
        return PaginationResponse.of(dtos, total, query.getPage(), query.getSize());
    }
}

