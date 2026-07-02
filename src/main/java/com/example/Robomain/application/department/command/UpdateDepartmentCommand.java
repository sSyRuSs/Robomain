package com.example.Robomain.application.department.command;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateDepartmentCommand {
    private UUID departmentId;
    @NotBlank(message = "Department name is required")
    private String departmentName;
}
