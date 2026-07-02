package com.example.Robomain.domain.maintenance.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Maintenance domain entity — a maintenance program that groups WorkOrders and Tasks.
 * Acts as a top-level container for maintenance activities.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {

    private UUID id;
    private String maintenanceName;
    private Date createdAt;
    private Date updatedAt;

    public static Maintenance create(String name) {
        if (name == null || name.isBlank()) throw new ValidationException("Maintenance name cannot be blank");
        return Maintenance.builder()
                .id(UUID.randomUUID())
                .maintenanceName(name)
                .build();
    }

    public void rename(String name) {
        if (name == null || name.isBlank()) throw new ValidationException("Maintenance name cannot be blank");
        this.maintenanceName = name;
    }
}
