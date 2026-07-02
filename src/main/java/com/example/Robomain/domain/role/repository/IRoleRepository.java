package com.example.Robomain.domain.role.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.role.model.Role;
import com.example.Robomain.domain.shared.enums.EnumRole;

/**
 * Domain interface for Role persistence.
 * Implementation lives in infrastructure layer.
 */
public interface IRoleRepository {

    Optional<Role> findById(UUID id);

    Optional<Role> findByName(EnumRole name);

    List<Role> findAll();

    Role save(Role role);
}
