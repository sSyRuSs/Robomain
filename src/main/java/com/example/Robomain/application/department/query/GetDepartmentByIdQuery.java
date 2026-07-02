package com.example.Robomain.application.department.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetDepartmentByIdQuery {
    private final UUID departmentId;
}
