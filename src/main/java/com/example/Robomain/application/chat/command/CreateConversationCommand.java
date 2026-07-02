package com.example.Robomain.application.chat.command;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateConversationCommand {
    @NotBlank private String type;    // DIRECT or GROUP
    private String name;
    private UUID departmentId;
    @NotNull private UUID enterpriseId;
    @NotNull private UUID createdBy;
}
