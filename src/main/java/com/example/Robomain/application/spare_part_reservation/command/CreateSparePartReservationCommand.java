package com.example.Robomain.application.spare_part_reservation.command;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateSparePartReservationCommand {
    @NotNull private UUID inventoryId;
    @NotNull private UUID workOrderId;
    private UUID taskId;
    @Min(1) private int quantity;
    private String note;
    private UUID reservedBy;
    @NotNull private UUID enterpriseId;
}
