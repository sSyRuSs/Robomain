package com.example.Robomain.application.response_issue.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ListResponseIssuesByIssueQuery {
    private final UUID issueId;
    private final UUID parentId;  // null = top-level responses only
    private final int page;
    private final int size;
}
