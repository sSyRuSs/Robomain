package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.role.model.Role;
import com.example.Robomain.domain.role.repository.IRoleRepository;
import com.example.Robomain.domain.shared.enums.EnumRole;
import com.example.Robomain.infrastructure.persistence.entity.RoleJpaEntity;
import com.example.Robomain.infrastructure.persistence.jpa.RoleJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements IRoleRepository {

    private final RoleJpaRepository jpaRepository;

    @Override
    public Optional<Role> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Role> findByName(EnumRole name) {
        return jpaRepository.findByRoleName(name).map(this::toDomain);
    }

    @Override
    public List<Role> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Role save(Role role) {
        RoleJpaEntity entity = RoleJpaEntity.builder().roleName(role.getName()).build();
        if (role.getId() != null) entity.setId(role.getId());
        return toDomain(jpaRepository.save(entity));
    }

    private Role toDomain(RoleJpaEntity entity) {
        return Role.of(entity.getId(), entity.getRoleName());
    }
}
