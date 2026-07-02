package com.example.Robomain.application.response_issue.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class ResponseIssueDto {
    private UUID id;
    private String responseTitle;
    private String responseDescription;
    private Boolean important;
    private UUID parentResponseId;
    private UUID issueId;
}
