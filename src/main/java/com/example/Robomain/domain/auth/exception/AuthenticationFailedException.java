package com.example.Robomain.domain.auth.exception;

/**
 * Thrown when authentication fails (invalid credentials, expired token, etc.)
 * Maps to HTTP 401 at the presentation layer.
 */
public class AuthenticationFailedException extends RuntimeException {

    private final String errorCode;

    public AuthenticationFailedException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
