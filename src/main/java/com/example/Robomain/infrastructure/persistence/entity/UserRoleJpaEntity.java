package com.example.Robomain.infrastructure.persistence.entity;

import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleJpaEntity {

    @EmbeddedId
    private UserRoleId id;
}
