package com.example.Robomain.application.user.dto;

import java.util.List;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO for representing a user in API responses.
 * Never exposes password or sensitive infrastructure fields.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String email;
    private String username;
    private String phone;
    private String address;
    private UUID departmentId;
    private UUID enterpriseId;
    private List<EnumRole> roles;
}
