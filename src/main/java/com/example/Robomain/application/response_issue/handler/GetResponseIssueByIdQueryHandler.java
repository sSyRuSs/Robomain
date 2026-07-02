package com.example.Robomain.application.response_issue.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.response_issue.dto.ResponseIssueDto;
import com.example.Robomain.application.response_issue.mapper.ResponseIssueDtoMapper;
import com.example.Robomain.application.response_issue.query.GetResponseIssueByIdQuery;
import com.example.Robomain.domain.response_issue.repository.IResponseIssueRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetResponseIssueByIdQueryHandler {

    private final IResponseIssueRepository responseIssueRepository;
    private final ResponseIssueDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public ResponseIssueDto handle(GetResponseIssueByIdQuery query) {
        return responseIssueRepository.findById(query.getResponseId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("ResponseIssue", query.getResponseId()));
    }
}

