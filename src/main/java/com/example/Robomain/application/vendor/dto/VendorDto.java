package com.example.Robomain.application.vendor.dto;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumVendorStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class VendorDto {
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
    private EnumVendorStatus status;
    private UUID enterpriseId;
}
