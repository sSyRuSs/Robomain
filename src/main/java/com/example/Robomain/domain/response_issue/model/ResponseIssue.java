package com.example.Robomain.domain.response_issue.model;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ResponseIssue — a response/comment on a TaskDetail issue.
 * Supports nested replies via self-referencing parentResponseId.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseIssue {

    private UUID id;
    private String responseTitle;
    private String responseDescription;
    @Builder.Default
    private Boolean important = false;
    private UUID parentResponseId;  // null = top-level response; non-null = reply
    private UUID issueId;           // FK to TaskDetail
    private Date createdAt;
    private Date updatedAt;

    public static ResponseIssue create(String responseTitle, String responseDescription,
                                        UUID issueId, UUID parentResponseId) {
        return ResponseIssue.builder()
                .id(UUID.randomUUID()).responseTitle(responseTitle).responseDescription(responseDescription)
                .important(false).parentResponseId(parentResponseId).issueId(issueId)
                .build();
    }

    public void update(String responseTitle, String responseDescription) {
        if (responseTitle != null && !responseTitle.isBlank()) this.responseTitle = responseTitle;
        if (responseDescription != null) this.responseDescription = responseDescription;
    }

    public void setImportant(boolean important) { this.important = important; }

    /** Returns empty if this is a top-level response. */
    public Optional<UUID> getParentResponseId() {
        return Optional.ofNullable(parentResponseId);
    }
}
