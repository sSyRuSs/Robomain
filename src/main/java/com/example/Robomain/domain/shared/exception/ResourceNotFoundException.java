package com.example.Robomain.domain.shared.exception;

import java.util.UUID;

/**
 * Thrown when a requested resource does not exist.
 * Maps to HTTP 404 at the presentation layer.
 */
public class ResourceNotFoundException extends DomainException {

    public ResourceNotFoundException(String resource, UUID id) {
        super(resource + " not found with id: " + id, "RESOURCE_NOT_FOUND");
    }

    public ResourceNotFoundException(String resource, String identifier) {
        super(resource + " not found: " + identifier, "RESOURCE_NOT_FOUND");
    }
}
