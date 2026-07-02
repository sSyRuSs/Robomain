package com.example.Robomain.application.response_issue.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.response_issue.dto.ResponseIssueDto;
import com.example.Robomain.domain.response_issue.model.ResponseIssue;

@Component
public class ResponseIssueDtoMapper {

    public ResponseIssueDto toDto(ResponseIssue ri) {
        return ResponseIssueDto.builder()
                .id(ri.getId()).responseTitle(ri.getResponseTitle())
                .responseDescription(ri.getResponseDescription()).important(ri.getImportant())
                .parentResponseId(ri.getParentResponseId().orElse(null)).issueId(ri.getIssueId())
                .build();
    }
}
