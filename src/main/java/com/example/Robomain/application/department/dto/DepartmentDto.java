package com.example.Robomain.application.department.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class DepartmentDto {
    private UUID id;
    private String departmentName;
}
