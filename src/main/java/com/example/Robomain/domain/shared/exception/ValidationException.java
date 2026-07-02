package com.example.Robomain.domain.shared.exception;

/**
 * Thrown when domain invariants or business rules are violated.
 * Maps to HTTP 400 at the presentation layer.
 */
public class ValidationException extends DomainException {

    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR");
    }

    public ValidationException(String message, String errorCode) {
        super(message, errorCode);
    }
}
