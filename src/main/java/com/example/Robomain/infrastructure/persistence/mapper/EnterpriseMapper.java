package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.enterprise.model.Enterprise;
import com.example.Robomain.infrastructure.persistence.entity.EnterpriseJpaEntity;

@Component
public class EnterpriseMapper {

    public Enterprise toDomain(EnterpriseJpaEntity e) {
        if (e == null) return null;
        return Enterprise.builder()
                .id(e.getId()).enterpriseName(e.getEnterpriseName())
                .enterpriseCode(e.getEnterpriseCode()).enterpriseMail(e.getEnterpriseMail())
                .enterprisePhone(e.getEnterprisePhone()).enterpriseAddress(e.getEnterpriseAddress())
                .facilityTotal(e.getFacilityTotal())
                .businessClassificationId(e.getBusinessClassificationId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public EnterpriseJpaEntity toJpa(Enterprise d) {
        if (d == null) return null;
        EnterpriseJpaEntity e = EnterpriseJpaEntity.builder()
                .enterpriseName(d.getEnterpriseName()).enterpriseCode(d.getEnterpriseCode())
                .enterpriseMail(d.getEnterpriseMail()).enterprisePhone(d.getEnterprisePhone())
                .enterpriseAddress(d.getEnterpriseAddress()).facilityTotal(d.getFacilityTotal())
                .businessClassificationId(d.getBusinessClassificationId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
