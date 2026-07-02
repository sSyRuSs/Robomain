package com.example.Robomain.application.auth.handler;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.auth.command.LoginCommand;
import com.example.Robomain.application.auth.dto.AuthResponse;
import com.example.Robomain.application.auth.port.IAuthenticationPort;
import com.example.Robomain.domain.auth.model.UserToken;
import com.example.Robomain.domain.auth.repository.IUserTokenRepository;

import lombok.RequiredArgsConstructor;

/**
 * Handles the login command — authenticates credentials and issues tokens.
 * No Spring Security types in this class — depends only on domain + application ports.
 */
@Service
@RequiredArgsConstructor
public class LoginCommandHandler {

    private static final Logger auditLogger = LoggerFactory.getLogger("SECURITY_AUDIT");

    private final IAuthenticationPort authenticationPort;
    private final IUserTokenRepository userTokenRepository;

    @Value("${app.refreshExpirationMs}")
    private long refreshExpirationMs;

    @Transactional
    public AuthResponse handle(LoginCommand command) {
        var result = authenticationPort.login(command.getEmail(), command.getPassword());

        String refreshToken = UUID.randomUUID() + "-" + System.currentTimeMillis();

        UserToken userToken = UserToken.builder()
                .userId(result.userId())
                .accessToken(result.accessToken())
                .refreshToken(refreshToken)
                .refreshTokenExpire(new Date(System.currentTimeMillis() + refreshExpirationMs))
                .isReseted(false)
                .build();

        userTokenRepository.save(userToken);

        auditLogger.info("LOGIN_SUCCESS: email={}, userId={}", command.getEmail(), result.userId());
        return AuthResponse.builder()
                .accessToken(result.accessToken())
                .refreshToken(refreshToken)
                .build();
    }
}
