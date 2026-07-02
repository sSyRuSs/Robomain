package com.example.Robomain.application.user.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Command to update an existing user's profile.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserCommand {

    private UUID userId;
    private String username;
    private String phone;
    private String address;
}
