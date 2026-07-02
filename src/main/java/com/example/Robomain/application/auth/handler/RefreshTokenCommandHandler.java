package com.example.Robomain.application.auth.handler;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.auth.command.RefreshTokenCommand;
import com.example.Robomain.application.auth.dto.AuthResponse;
import com.example.Robomain.application.auth.port.IAuthenticationPort;
import com.example.Robomain.domain.auth.exception.AuthenticationFailedException;
import com.example.Robomain.domain.auth.model.UserToken;
import com.example.Robomain.domain.auth.repository.IUserTokenRepository;
import com.example.Robomain.domain.user.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Issues a new access token given a valid refresh token.
 * Pure application logic — no Spring Security imports.
 */
@Service
@RequiredArgsConstructor
public class RefreshTokenCommandHandler {

    private static final Logger auditLogger = LoggerFactory.getLogger("SECURITY_AUDIT");

    private final IUserTokenRepository userTokenRepository;
    private final IUserRepository userRepository;
    private final IAuthenticationPort authenticationPort;

    @Value("${app.refreshExpirationMs}")
    private long refreshExpirationMs;

    @Transactional
    public AuthResponse handle(RefreshTokenCommand command) {
        var existingToken = userTokenRepository.findByRefreshToken(command.getRefreshToken())
                .orElseThrow(() -> new AuthenticationFailedException("Refresh token not found", "REFRESH_TOKEN_NOT_FOUND"));

        if (existingToken.isRevoked()) {
            throw new AuthenticationFailedException("Refresh token revoked", "REFRESH_TOKEN_REVOKED");
        }
        if (existingToken.isRefreshTokenExpired()) {
            userTokenRepository.delete(existingToken);
            throw new AuthenticationFailedException("Refresh token expired", "REFRESH_TOKEN_EXPIRED");
        }

        var user = userRepository.findById(existingToken.getUserId())
                .orElseThrow(() -> new AuthenticationFailedException("User not found", "USER_NOT_FOUND"));

        // Generate new token via port — no Spring Security types in this layer
        String newAccessToken = authenticationPort.generateTokenForUser(user.getId(), user.getEmail());
        String newRefreshToken = UUID.randomUUID() + "-" + System.currentTimeMillis();

        // Save new token before revoking old one (prevents token loss on failure)
        UserToken newToken = UserToken.builder()
                .userId(user.getId())
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .refreshTokenExpire(new Date(System.currentTimeMillis() + refreshExpirationMs))
                .isReseted(false)
                .build();
        userTokenRepository.save(newToken);

        existingToken.revoke();
        userTokenRepository.save(existingToken);

        auditLogger.info("TOKEN_REFRESH_SUCCESS: userId={}", user.getId());
        return AuthResponse.builder().accessToken(newAccessToken).refreshToken(newRefreshToken).build();
    }
}
