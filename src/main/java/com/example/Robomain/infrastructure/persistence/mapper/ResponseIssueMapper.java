package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.response_issue.model.ResponseIssue;
import com.example.Robomain.infrastructure.persistence.entity.ResponseIssueJpaEntity;

@Component
public class ResponseIssueMapper {

    public ResponseIssue toDomain(ResponseIssueJpaEntity e) {
        if (e == null) return null;
        return ResponseIssue.builder()
                .id(e.getId()).responseTitle(e.getResponseTitle())
                .responseDescription(e.getResponseDescription()).important(e.getImportant())
                .parentResponseId(e.getParentResponseId()).issueId(e.getIssueId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public ResponseIssueJpaEntity toJpa(ResponseIssue d) {
        if (d == null) return null;
        ResponseIssueJpaEntity e = ResponseIssueJpaEntity.builder()
                .responseTitle(d.getResponseTitle()).responseDescription(d.getResponseDescription())
                .important(d.getImportant())
                .parentResponseId(d.getParentResponseId().orElse(null))
                .issueId(d.getIssueId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
