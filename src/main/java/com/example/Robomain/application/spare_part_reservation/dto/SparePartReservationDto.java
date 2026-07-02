package com.example.Robomain.application.spare_part_reservation.dto;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumSparePartReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class SparePartReservationDto {
    private UUID id;
    private UUID inventoryId;
    private UUID workOrderId;
    private UUID taskId;
    private int quantity;
    private EnumSparePartReservationStatus status;
    private Date reservedAt;
    private Date issuedAt;
    private Date cancelledAt;
    private String note;
    private UUID reservedBy;
    private UUID enterpriseId;
}
