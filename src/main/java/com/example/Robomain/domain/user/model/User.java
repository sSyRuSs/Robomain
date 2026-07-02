package com.example.Robomain.domain.user.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.Robomain.domain.role.model.Role;
import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User domain entity — pure Java, no JPA/Spring annotations.
 * 
 * Why: Domain entities must be framework-agnostic.
 * Business rules live here, not in services or controllers.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;
    private String email;
    private String username;
    private String phone;
    private String address;
    private String password;
    private UUID departmentId;
    private UUID enterpriseId;

    @Builder.Default
    private List<Role> roles = new ArrayList<>();

    private Date createdAt;
    private Date updatedAt;

    // Factory method with business rule enforcement
    public static User create(String email, String username, String hashedPassword) {
        validateEmail(email);
        if (username == null || username.isBlank()) {
            throw new ValidationException("Username cannot be blank");
        }
        return User.builder()
                .id(UUID.randomUUID())
                .email(email)
                .username(username)
                .password(hashedPassword)
                .roles(new ArrayList<>())
                .build();
    }

    public void assignRole(Role role) {
        if (roles.stream().anyMatch(r -> r.getName() == role.getName())) {
            return; // idempotent — already has this role
        }
        roles.add(role);
    }

    public void updateProfile(String username, String phone, String address) {
        if (username != null && !username.isBlank()) this.username = username;
        if (phone != null) this.phone = phone;
        if (address != null) this.address = address;
    }

    public void changePassword(String newHashedPassword) {
        if (newHashedPassword == null || newHashedPassword.isBlank()) {
            throw new ValidationException("Password cannot be blank");
        }
        this.password = newHashedPassword;
    }

    private static void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new ValidationException("Invalid email format: " + email);
        }
    }
}
