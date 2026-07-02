package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.vendor.model.Vendor;
import com.example.Robomain.infrastructure.persistence.entity.VendorJpaEntity;

@Component
public class VendorMapper {

    public Vendor toDomain(VendorJpaEntity e) {
        if (e == null) return null;
        return Vendor.builder()
                .id(e.getId()).vendorCode(e.getVendorCode()).vendorName(e.getVendorName())
                .description(e.getDescription()).contactPerson(e.getContactPerson()).email(e.getEmail())
                .phoneNumber(e.getPhoneNumber()).address(e.getAddress()).city(e.getCity())
                .country(e.getCountry()).paymentTerms(e.getPaymentTerms()).taxId(e.getTaxId())
                .status(e.getStatus()).enterpriseId(e.getEnterpriseId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public VendorJpaEntity toJpa(Vendor d) {
        if (d == null) return null;
        VendorJpaEntity e = VendorJpaEntity.builder()
                .vendorCode(d.getVendorCode()).vendorName(d.getVendorName())
                .description(d.getDescription()).contactPerson(d.getContactPerson()).email(d.getEmail())
                .phoneNumber(d.getPhoneNumber()).address(d.getAddress()).city(d.getCity())
                .country(d.getCountry()).paymentTerms(d.getPaymentTerms()).taxId(d.getTaxId())
                .status(d.getStatus()).enterpriseId(d.getEnterpriseId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
