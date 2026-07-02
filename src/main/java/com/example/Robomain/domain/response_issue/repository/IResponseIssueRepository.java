package com.example.Robomain.domain.response_issue.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.response_issue.model.ResponseIssue;

public interface IResponseIssueRepository {
    Optional<ResponseIssue> findById(UUID id);
    List<ResponseIssue> findByIssueId(UUID issueId, UUID parentId, int page, int size);
    long countByIssueId(UUID issueId, UUID parentId);
    ResponseIssue save(ResponseIssue responseIssue);
    void deleteById(UUID id);
}
