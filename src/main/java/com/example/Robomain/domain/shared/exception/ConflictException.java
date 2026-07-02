package com.example.Robomain.domain.shared.exception;

/**
 * Thrown when an operation conflicts with existing state (e.g. duplicate resource).
 * Maps to HTTP 409 at the presentation layer.
 */
public class ConflictException extends DomainException {

    public ConflictException(String message) {
        super(message, "CONFLICT");
    }

    public ConflictException(String resource, String field, String value) {
        super(resource + " already exists with " + field + ": " + value, "CONFLICT");
    }
}
