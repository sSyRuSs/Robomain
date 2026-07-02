package com.example.Robomain.application.auth.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.auth.command.LogoutCommand;
import com.example.Robomain.domain.auth.exception.AuthenticationFailedException;
import com.example.Robomain.domain.auth.repository.IUserTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutCommandHandler {

    private static final Logger auditLogger = LoggerFactory.getLogger("SECURITY_AUDIT");

    private final IUserTokenRepository userTokenRepository;

    @Transactional
    public void handle(LogoutCommand command) {
        String header = command.getAuthorizationHeader();
        if (header == null || !header.startsWith("Bearer ")) {
            throw new AuthenticationFailedException("Invalid authorization header", "INVALID_TOKEN_FORMAT");
        }

        String token = header.substring(7);
        var userToken = userTokenRepository.findByAccessToken(token)
                .orElseThrow(() -> new AuthenticationFailedException("Token not found", "TOKEN_NOT_FOUND"));

        userTokenRepository.delete(userToken);
        auditLogger.info("LOGOUT_SUCCESS: userId={}", userToken.getUserId());
    }
}
