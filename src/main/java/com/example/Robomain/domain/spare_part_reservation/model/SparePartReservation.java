package com.example.Robomain.domain.spare_part_reservation.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumSparePartReservationStatus;
import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * SparePartReservation — reserves inventory parts for a specific WorkOrder/Task.
 * Lifecycle: RESERVED → ISSUED (parts physically taken) or CANCELLED.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SparePartReservation {

    private UUID id;
    private UUID inventoryId;
    private UUID workOrderId;
    private UUID taskId;        // optional
    private int quantity;
    @Builder.Default
    private EnumSparePartReservationStatus status = EnumSparePartReservationStatus.RESERVED;
    private Date reservedAt;
    private Date issuedAt;
    private Date cancelledAt;
    private String note;
    private UUID reservedBy;
    private UUID enterpriseId;
    private Date createdAt;
    private Date updatedAt;

    public static SparePartReservation create(UUID inventoryId, UUID workOrderId, UUID taskId,
                                               int quantity, String note, UUID reservedBy, UUID enterpriseId) {
        if (inventoryId == null) throw new ValidationException("Inventory item is required");
        if (workOrderId == null) throw new ValidationException("Work order is required");
        if (quantity <= 0) throw new ValidationException("Quantity must be positive");
        if (enterpriseId == null) throw new ValidationException("Enterprise is required");
        return SparePartReservation.builder()
                .id(UUID.randomUUID()).inventoryId(inventoryId).workOrderId(workOrderId)
                .taskId(taskId).quantity(quantity).note(note)
                .status(EnumSparePartReservationStatus.RESERVED)
                .reservedAt(new Date()).reservedBy(reservedBy).enterpriseId(enterpriseId)
                .build();
    }

    /** Issues the reserved parts — marks physically taken from stock. */
    public void issue() {
        if (this.status != EnumSparePartReservationStatus.RESERVED) {
            throw new ValidationException("Can only issue a RESERVED spare part, current: " + this.status);
        }
        this.status = EnumSparePartReservationStatus.ISSUED;
        this.issuedAt = new Date();
    }

    /** Cancels the reservation. Can be cancelled from RESERVED or ISSUED (return scenario). */
    public void cancel() {
        if (this.status == EnumSparePartReservationStatus.CANCELLED) {
            throw new ValidationException("Spare part reservation is already cancelled");
        }
        this.status = EnumSparePartReservationStatus.CANCELLED;
        this.cancelledAt = new Date();
    }
}
