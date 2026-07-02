package com.example.Robomain.application.auth.command;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Command to issue a new access token using a valid refresh token.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenCommand {

    @NotBlank(message = "Refresh token is required")
    private String refreshToken;
}
