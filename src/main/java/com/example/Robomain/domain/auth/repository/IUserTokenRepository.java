package com.example.Robomain.domain.auth.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.auth.model.UserToken;

/**
 * Domain interface for UserToken persistence.
 * Implementation in infrastructure layer.
 */
public interface IUserTokenRepository {

    Optional<UserToken> findByAccessToken(String accessToken);

    Optional<UserToken> findByRefreshToken(String refreshToken);

    UserToken save(UserToken token);

    void delete(UserToken token);

    void deleteByUserId(UUID userId);
}
