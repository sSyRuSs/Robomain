package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumRole;
import com.example.Robomain.infrastructure.persistence.entity.RoleJpaEntity;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleJpaEntity, UUID> {

    Optional<RoleJpaEntity> findByRoleName(EnumRole roleName);
}
