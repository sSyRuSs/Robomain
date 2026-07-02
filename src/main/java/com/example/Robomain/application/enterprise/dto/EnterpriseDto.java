package com.example.Robomain.application.enterprise.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class EnterpriseDto {
    private UUID id;
    private String enterpriseName;
    private String enterpriseCode;
    private String enterpriseMail;
    private String enterprisePhone;
    private String enterpriseAddress;
    private int facilityTotal;
    private UUID businessClassificationId;
}
