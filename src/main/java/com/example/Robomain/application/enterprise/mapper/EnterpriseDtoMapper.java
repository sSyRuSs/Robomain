package com.example.Robomain.application.enterprise.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.enterprise.dto.EnterpriseDto;
import com.example.Robomain.domain.enterprise.model.Enterprise;

@Component
public class EnterpriseDtoMapper {

    public EnterpriseDto toDto(Enterprise e) {
        return EnterpriseDto.builder()
                .id(e.getId()).enterpriseName(e.getEnterpriseName())
                .enterpriseCode(e.getEnterpriseCode()).enterpriseMail(e.getEnterpriseMail())
                .enterprisePhone(e.getEnterprisePhone()).enterpriseAddress(e.getEnterpriseAddress())
                .facilityTotal(e.getFacilityTotal())
                .businessClassificationId(e.getBusinessClassificationId())
                .build();
    }
}
