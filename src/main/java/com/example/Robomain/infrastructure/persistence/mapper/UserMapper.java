package com.example.Robomain.infrastructure.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.role.model.Role;
import com.example.Robomain.domain.user.model.User;
import com.example.Robomain.infrastructure.persistence.entity.UserJpaEntity;
import com.example.Robomain.infrastructure.persistence.entity.UserRoleJpaEntity;

/**
 * Maps between User domain model and UserJpaEntity.
 * Explicit mapping — no reflection magic (Karpathy principle).
 */
@Component
public class UserMapper {

    public User toDomain(UserJpaEntity entity) {
        if (entity == null) return null;

        List<Role> roles = new ArrayList<>();
        if (entity.getUserRoles() != null) {
            roles = entity.getUserRoles().stream()
                    .filter(ur -> ur.getId() != null && ur.getId().getRole() != null)
                    .map(ur -> Role.of(ur.getId().getRole().getId(), ur.getId().getRole().getRoleName()))
                    .toList();
        }

        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .departmentId(entity.getDepartmentId())
                .enterpriseId(entity.getEnterpriseId())
                .roles(roles)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public UserJpaEntity toJpa(User domain) {
        if (domain == null) return null;
        return UserJpaEntity.builder()
                .email(domain.getEmail())
                .username(domain.getUsername())
                .password(domain.getPassword())
                .phone(domain.getPhone())
                .address(domain.getAddress())
                .departmentId(domain.getDepartmentId())
                .enterpriseId(domain.getEnterpriseId())
                .build();
    }
}
