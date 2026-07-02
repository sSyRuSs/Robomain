package com.example.Robomain.domain.department.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Department domain entity.
 * Departments group users within the system; they exist independently of enterprises.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private UUID id;
    private String departmentName;
    private Date createdAt;
    private Date updatedAt;

    public static Department create(String name) {
        if (name == null || name.isBlank()) throw new ValidationException("Department name cannot be blank");
        return Department.builder()
                .id(UUID.randomUUID())
                .departmentName(name)
                .build();
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) throw new ValidationException("Department name cannot be blank");
        this.departmentName = newName;
    }
}
