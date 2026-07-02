package com.example.Robomain.application.response_issue.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.response_issue.dto.ResponseIssueDto;
import com.example.Robomain.application.response_issue.mapper.ResponseIssueDtoMapper;
import com.example.Robomain.application.response_issue.query.ListResponseIssuesByIssueQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.response_issue.repository.IResponseIssueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListResponseIssuesByIssueQueryHandler {

    private final IResponseIssueRepository responseIssueRepository;
    private final ResponseIssueDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<ResponseIssueDto> handle(ListResponseIssuesByIssueQuery query) {
        var items = responseIssueRepository.findByIssueId(query.getIssueId(), query.getParentId(),
                query.getPage(), query.getSize());
        var total = responseIssueRepository.countByIssueId(query.getIssueId(), query.getParentId());
        var dtos = items.stream().map(dtoMapper::toDto).toList();
        return PaginationResponse.of(dtos, total, query.getPage(), query.getSize());
    }
}

