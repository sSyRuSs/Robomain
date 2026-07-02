package com.example.Robomain.infrastructure.persistence.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Robomain.infrastructure.persistence.entity.UserTokenJpaEntity;

@Repository
public interface UserTokenJpaRepository extends JpaRepository<UserTokenJpaEntity, UUID> {

    Optional<UserTokenJpaEntity> findByAccessToken(String accessToken);

    Optional<UserTokenJpaEntity> findByRefreshToken(String refreshToken);

    void deleteByUserId(UUID userId);
}
