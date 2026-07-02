package com.example.Robomain.domain.vendor.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumVendorStatus;
import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Vendor domain entity — a supplier of parts/services for an enterprise.
 * Scoped per enterprise. Tracks contact details and operational status.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {

    private UUID id;
    private String vendorCode;
    private String vendorName;
    private String description;
    private String contactPerson;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String paymentTerms;
    private String taxId;
    @Builder.Default
    private EnumVendorStatus status = EnumVendorStatus.ACTIVE;
    private UUID enterpriseId;
    private Date createdAt;
    private Date updatedAt;

    public static Vendor create(String vendorCode, String vendorName, UUID enterpriseId) {
        if (vendorCode == null || vendorCode.isBlank()) throw new ValidationException("Vendor code cannot be blank");
        if (vendorName == null || vendorName.isBlank()) throw new ValidationException("Vendor name cannot be blank");
        if (enterpriseId == null) throw new ValidationException("Vendor must belong to an enterprise");
        return Vendor.builder()
                .id(UUID.randomUUID())
                .vendorCode(vendorCode).vendorName(vendorName)
                .status(EnumVendorStatus.ACTIVE).enterpriseId(enterpriseId)
                .build();
    }

    public void update(String vendorName, String description, String contactPerson, String email,
                       String phoneNumber, String address, String city, String country,
                       String paymentTerms, String taxId) {
        if (vendorName != null && !vendorName.isBlank()) this.vendorName = vendorName;
        if (description != null) this.description = description;
        if (contactPerson != null) this.contactPerson = contactPerson;
        if (email != null) this.email = email;
        if (phoneNumber != null) this.phoneNumber = phoneNumber;
        if (address != null) this.address = address;
        if (city != null) this.city = city;
        if (country != null) this.country = country;
        if (paymentTerms != null) this.paymentTerms = paymentTerms;
        if (taxId != null) this.taxId = taxId;
    }

    public void changeStatus(EnumVendorStatus newStatus) {
        if (newStatus != null) this.status = newStatus;
    }
}
