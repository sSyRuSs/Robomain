package com.example.Robomain.infrastructure.persistence.entity;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_token")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenJpaEntity extends BaseJpaEntity {

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "access_token", length = 2048)
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "refresh_token_expire")
    private Date refreshTokenExpire;

    @Column(name = "is_reseted")
    private Boolean isReseted;
}
