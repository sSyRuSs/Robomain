package com.example.Robomain.application.vendor.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.vendor.dto.VendorDto;
import com.example.Robomain.domain.vendor.model.Vendor;

@Component
public class VendorDtoMapper {

    public VendorDto toDto(Vendor v) {
        return VendorDto.builder()
                .id(v.getId()).vendorCode(v.getVendorCode()).vendorName(v.getVendorName())
                .description(v.getDescription()).contactPerson(v.getContactPerson()).email(v.getEmail())
                .phoneNumber(v.getPhoneNumber()).address(v.getAddress()).city(v.getCity())
                .country(v.getCountry()).paymentTerms(v.getPaymentTerms()).taxId(v.getTaxId())
                .status(v.getStatus()).enterpriseId(v.getEnterpriseId())
                .build();
    }
}
