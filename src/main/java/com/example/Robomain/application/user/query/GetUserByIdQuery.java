package com.example.Robomain.application.user.query;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Query to retrieve a single user by ID. Read-only — no state change.
 */
@Getter
@AllArgsConstructor
public class GetUserByIdQuery {
    private final UUID userId;
}
