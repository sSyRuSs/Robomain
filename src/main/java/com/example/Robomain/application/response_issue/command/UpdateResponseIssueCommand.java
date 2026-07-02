package com.example.Robomain.application.response_issue.command;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateResponseIssueCommand {
    private UUID responseId;
    private String responseTitle;
    private String responseDescription;
    private Boolean important;
}
