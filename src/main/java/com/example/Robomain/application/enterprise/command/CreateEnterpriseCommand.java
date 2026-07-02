package com.example.Robomain.application.enterprise.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateEnterpriseCommand {
    @NotBlank(message = "Enterprise name is required")
    private String enterpriseName;
    private String enterpriseCode;
    @NotBlank(message = "Enterprise email is required")
    @Email(message = "Invalid email format")
    private String enterpriseMail;
    @NotBlank(message = "Enterprise phone is required")
    private String enterprisePhone;
    @NotBlank(message = "Enterprise address is required")
    private String enterpriseAddress;
    private java.util.UUID businessClassificationId;
}
