package com.example.Robomain.domain.facility.model;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Facility domain entity — a physical location belonging to an enterprise.
 * Can have a parent facility for hierarchical organization (sub-facility).
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Facility {

    private UUID id;
    private String facilityName;
    private String facilityCode;
    private String facilityAddress;
    private String facilityPhone;
    private Integer totalSubFacility;
    private UUID enterpriseId;
    private UUID parentFacilityId;   // self-reference — null means top-level
    private UUID assetCategoryId;    // optional categorization
    private Date createdAt;
    private Date updatedAt;

    public static Facility create(String name, String code, UUID enterpriseId) {
        if (name == null || name.isBlank()) throw new ValidationException("Facility name cannot be blank");
        if (enterpriseId == null) throw new ValidationException("Facility must belong to an enterprise");
        return Facility.builder()
                .id(UUID.randomUUID())
                .facilityName(name)
                .facilityCode(code)
                .enterpriseId(enterpriseId)
                .totalSubFacility(0)
                .build();
    }

    public void update(String name, String address, String phone) {
        if (name != null && !name.isBlank()) this.facilityName = name;
        if (address != null) this.facilityAddress = address;
        if (phone != null) this.facilityPhone = phone;
    }

    public void setParent(UUID parentId) {
        if (parentId != null && parentId.equals(this.id)) {
            throw new ValidationException("Facility cannot be its own parent");
        }
        this.parentFacilityId = parentId;
    }

    public Optional<UUID> getParentFacilityId() {
        return Optional.ofNullable(parentFacilityId);
    }
}
