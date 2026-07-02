package com.example.Robomain.application.auth.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Command to authenticate a user and issue tokens.
 * Write operation — changes system state by creating a new UserToken.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginCommand {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
