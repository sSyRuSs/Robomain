package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.auth.model.UserToken;
import com.example.Robomain.infrastructure.persistence.entity.UserTokenJpaEntity;

@Component
public class UserTokenMapper {

    public UserToken toDomain(UserTokenJpaEntity entity) {
        if (entity == null) return null;
        return UserToken.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .accessToken(entity.getAccessToken())
                .refreshToken(entity.getRefreshToken())
                .refreshTokenExpire(entity.getRefreshTokenExpire())
                .isReseted(entity.getIsReseted())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public UserTokenJpaEntity toJpa(UserToken domain) {
        if (domain == null) return null;
        UserTokenJpaEntity entity = UserTokenJpaEntity.builder()
                .userId(domain.getUserId())
                .accessToken(domain.getAccessToken())
                .refreshToken(domain.getRefreshToken())
                .refreshTokenExpire(domain.getRefreshTokenExpire())
                .isReseted(domain.getIsReseted())
                .build();
        if (domain.getId() != null) entity.setId(domain.getId());
        return entity;
    }
}
