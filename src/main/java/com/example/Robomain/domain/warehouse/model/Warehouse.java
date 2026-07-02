package com.example.Robomain.domain.warehouse.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumWarehouseType;
import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Warehouse domain entity — physical location where inventory is stored.
 * Scoped per enterprise. Linked to a Facility.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    private UUID id;
    private String warehouseCode;
    private String warehouseName;
    private String address;
    private UUID facilityId;
    private String managerName;
    private String contactPhone;
    private String contactEmail;
    private EnumWarehouseType type;
    private UUID enterpriseId;
    private Date createdAt;
    private Date updatedAt;

    public static Warehouse create(String code, String name, EnumWarehouseType type, UUID enterpriseId) {
        if (code == null || code.isBlank()) throw new ValidationException("Warehouse code cannot be blank");
        if (name == null || name.isBlank()) throw new ValidationException("Warehouse name cannot be blank");
        if (type == null) throw new ValidationException("Warehouse type is required");
        if (enterpriseId == null) throw new ValidationException("Warehouse must belong to an enterprise");
        return Warehouse.builder()
                .id(UUID.randomUUID())
                .warehouseCode(code).warehouseName(name)
                .type(type).enterpriseId(enterpriseId).build();
    }

    public void update(String name, String address, String managerName,
                       String contactPhone, String contactEmail) {
        if (name != null && !name.isBlank()) this.warehouseName = name;
        if (address != null) this.address = address;
        if (managerName != null) this.managerName = managerName;
        if (contactPhone != null) this.contactPhone = contactPhone;
        if (contactEmail != null) this.contactEmail = contactEmail;
    }
}
