package com.example.Robomain.application.shared.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Wraps paginated list results with total page count.
 * Used by all list query handlers.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse<T> {

    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int pageSize;

    public static <T> PaginationResponse<T> of(List<T> content, long totalElements, int page, int pageSize) {
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);
        return new PaginationResponse<>(content, totalPages, totalElements, page, pageSize);
    }
}
