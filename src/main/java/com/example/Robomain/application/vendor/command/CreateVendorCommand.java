package com.example.Robomain.application.vendor.command;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateVendorCommand {
    @NotBlank private String vendorCode;
    @NotBlank private String vendorName;
    private String description;
    private String contactPerson;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String paymentTerms;
    private String taxId;
    @NotNull private UUID enterpriseId;
}
