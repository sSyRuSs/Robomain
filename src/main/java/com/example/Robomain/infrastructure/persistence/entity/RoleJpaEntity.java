package com.example.Robomain.infrastructure.persistence.entity;

import com.example.Robomain.domain.shared.enums.EnumRole;
import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleJpaEntity extends BaseJpaEntity {

    @Enumerated(EnumType.STRING)
    private EnumRole roleName;
}
