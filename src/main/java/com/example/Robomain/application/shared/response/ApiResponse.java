package com.example.Robomain.application.shared.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Uniform API response wrapper for all endpoints.
 * Every controller should return ApiResponse<T>.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApiResponse<T> {

    private boolean success;
    private String code;
    private String message;
    private T data;
    private Instant timestamp;

    public static <T> ApiResponse<T> success(T data) {
        return success(data, "SUCCESS", "OK");
    }

    public static <T> ApiResponse<T> success(T data, String message, String code) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(code)
                .message(message)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> failure(String code, String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .data(null)
                .timestamp(Instant.now())
                .build();
    }
}
