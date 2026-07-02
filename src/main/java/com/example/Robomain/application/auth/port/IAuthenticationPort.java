package com.example.Robomain.application.auth.port;

import java.util.UUID;

/**
 * Application port for credential verification and token generation.
 * Decouples application handlers from Spring Security internals.
 * Implemented in infrastructure/security.
 */
public interface IAuthenticationPort {

    /**
     * Authenticates credentials and generates an access token in one step.
     * Returns a self-contained result — no Spring Security types leak into application.
     */
    LoginResult login(String email, String password);

    /**
     * Generates a new access token for a known/trusted user (used in refresh-token flow).
     */
    String generateTokenForUser(UUID userId, String email);

    /**
     * Result of a successful login: token + the user's ID.
     */
    record LoginResult(String accessToken, UUID userId) {}
}
