package com.example.Robomain.domain.role.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Role domain entity — pure Java, no JPA/Spring.
 * Represents a security role that can be assigned to users.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private UUID id;
    private EnumRole name;
    private Date createdAt;
    private Date updatedAt;

    public static Role of(UUID id, EnumRole name) {
        return Role.builder().id(id).name(name).build();
    }
}
