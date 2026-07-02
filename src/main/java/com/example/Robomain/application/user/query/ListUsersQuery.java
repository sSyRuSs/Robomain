package com.example.Robomain.application.user.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Query to list users with pagination.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListUsersQuery {
    private int page = 0;
    private int size = 10;
    private String search;
}
