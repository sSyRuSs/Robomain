package com.example.Robomain.application.auth.command;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Command to invalidate a user's access token (logout).
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LogoutCommand {

    @NotBlank(message = "Authorization header is required")
    private String authorizationHeader;
}
