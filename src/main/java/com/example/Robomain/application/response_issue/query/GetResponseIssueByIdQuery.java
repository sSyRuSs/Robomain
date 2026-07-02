package com.example.Robomain.application.response_issue.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetResponseIssueByIdQuery {
    private final UUID responseId;
}
