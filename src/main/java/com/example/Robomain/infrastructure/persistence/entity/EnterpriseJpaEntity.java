package com.example.Robomain.infrastructure.persistence.entity;

import java.util.UUID;

import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enterprise")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class EnterpriseJpaEntity extends BaseJpaEntity {

    @Column(name = "enterprise_name", nullable = false)
    private String enterpriseName;

    @Column(name = "enterprise_code")
    private String enterpriseCode;

    @Column(name = "enterprise_email")
    private String enterpriseMail;

    @Column(name = "enterprise_phone")
    private String enterprisePhone;

    @Column(name = "enterprise_address")
    private String enterpriseAddress;

    @Column(name = "facility_total")
    private int facilityTotal;

    @Column(name = "business_classification_id")
    private UUID businessClassificationId;
}
