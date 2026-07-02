package com.example.Robomain.application.enterprise.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateEnterpriseCommand {
    private UUID enterpriseId;
    private String enterpriseName;
    private String enterpriseCode;
    private String enterpriseMail;
    private String enterprisePhone;
    private String enterpriseAddress;
}
