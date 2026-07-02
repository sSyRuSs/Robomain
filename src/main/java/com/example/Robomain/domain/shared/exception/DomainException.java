package com.example.Robomain.domain.shared.exception;

/**
 * Base exception for all domain layer violations.
 * All domain-specific exceptions must extend this class.
 * 
 * Why: Enables consistent exception handling at the presentation layer
 * and clearly signals that an error originated in business logic.
 */
public abstract class DomainException extends RuntimeException {

    private final String errorCode;

    protected DomainException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
