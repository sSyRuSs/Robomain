package com.example.Robomain.infrastructure.persistence.entity;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumVendorStatus;
import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendor")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class VendorJpaEntity extends BaseJpaEntity {

    @Column(name = "vendor_code", nullable = false, length = 50)
    private String vendorCode;

    @Column(name = "vendor_name", nullable = false, length = 255)
    private String vendorName;

    @Column(name = "vendor_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "contact_person", length = 255)
    private String contactPerson;

    @Column(length = 255)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "vendor_address", length = 500)
    private String address;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String country;

    @Column(name = "payment_terms", length = 100)
    private String paymentTerms;

    @Column(name = "tax_id", length = 50)
    private String taxId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumVendorStatus status;

    @Column(name = "enterprise_id", nullable = false)
    private UUID enterpriseId;
}
