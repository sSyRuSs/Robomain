package com.example.Robomain.application.vendor.command;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumVendorStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateVendorCommand {
    private UUID vendorId;
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
    private EnumVendorStatus status;
}
