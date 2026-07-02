package com.example.Robomain.application.response_issue.command;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateResponseIssueCommand {
    @NotBlank private String responseTitle;
    private String responseDescription;
    private Boolean important;
    private UUID parentResponseId;  // null = top-level response
    @NotNull private UUID issueId;
}
