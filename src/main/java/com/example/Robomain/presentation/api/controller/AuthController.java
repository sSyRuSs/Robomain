package com.example.Robomain.presentation.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Robomain.application.auth.command.LoginCommand;
import com.example.Robomain.application.auth.command.LogoutCommand;
import com.example.Robomain.application.auth.command.RefreshTokenCommand;
import com.example.Robomain.application.auth.dto.AuthResponse;
import com.example.Robomain.application.auth.handler.LoginCommandHandler;
import com.example.Robomain.application.auth.handler.LogoutCommandHandler;
import com.example.Robomain.application.auth.handler.RefreshTokenCommandHandler;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.infrastructure.security.RateLimitService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.AUTH_PATH)
@RequiredArgsConstructor
public class AuthController {

    private static final Logger auditLogger = LoggerFactory.getLogger("SECURITY_AUDIT");

    private final LoginCommandHandler loginHandler;
    private final LogoutCommandHandler logoutHandler;
    private final RefreshTokenCommandHandler refreshTokenHandler;
    private final RateLimitService rateLimitService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginCommand command,
            HttpServletRequest request) {

        String clientIp = getClientIp(request);
        String identifier = command.getEmail() + "_" + clientIp;

        if (!rateLimitService.isAllowed(identifier)) {
            auditLogger.warn("RATE_LIMIT_EXCEEDED: email={}, ip={}", command.getEmail(), clientIp);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(ApiResponse.failure("RATE_LIMIT_EXCEEDED", "Too many attempts. Try again later."));
        }

        try {
            AuthResponse response = loginHandler.handle(command);
            rateLimitService.recordSuccessfulLogin(identifier);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (BadCredentialsException e) {
            rateLimitService.recordFailedAttempt(identifier);
            int remaining = rateLimitService.getRemainingAttempts(identifier);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.failure("BAD_CREDENTIALS",
                            remaining > 0 ? remaining + " attempts remaining." : "Account blocked."));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String authHeader) {
        logoutHandler.handle(new LogoutCommand(authHeader));
        return ResponseEntity.ok(ApiResponse.success(null, "Logged out successfully", "SUCCESS"));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(
            @Valid @RequestBody RefreshTokenCommand command) {
        return ResponseEntity.ok(ApiResponse.success(refreshTokenHandler.handle(command)));
    }

    private String getClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isEmpty()) return forwarded.split(",")[0].trim();
        String realIp = request.getHeader("X-Real-IP");
        if (realIp != null && !realIp.isEmpty()) return realIp;
        return request.getRemoteAddr();
    }
}
