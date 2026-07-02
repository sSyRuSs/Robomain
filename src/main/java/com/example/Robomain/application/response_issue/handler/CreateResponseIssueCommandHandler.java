package com.example.Robomain.application.response_issue.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.response_issue.command.CreateResponseIssueCommand;
import com.example.Robomain.application.response_issue.mapper.ResponseIssueDtoMapper;
import com.example.Robomain.domain.response_issue.model.ResponseIssue;
import com.example.Robomain.domain.response_issue.repository.IResponseIssueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateResponseIssueCommandHandler {

    private final IResponseIssueRepository responseIssueRepository;
    private final ResponseIssueDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateResponseIssueCommand cmd) {
        ResponseIssue ri = ResponseIssue.create(cmd.getResponseTitle(), cmd.getResponseDescription(),
                cmd.getIssueId(), cmd.getParentResponseId());
        if (Boolean.TRUE.equals(cmd.getImportant())) ri.setImportant(true);
        return responseIssueRepository.save(ri).getId();
    }
}
