package com.example.Robomain.application.department.command;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateDepartmentCommand {
    @NotBlank(message = "Department name is required")
    private String departmentName;
}
