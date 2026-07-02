package com.example.Robomain.application.response_issue.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.response_issue.command.UpdateResponseIssueCommand;
import com.example.Robomain.application.response_issue.mapper.ResponseIssueDtoMapper;
import com.example.Robomain.domain.response_issue.repository.IResponseIssueRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateResponseIssueCommandHandler {

    private final IResponseIssueRepository responseIssueRepository;
    private final ResponseIssueDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateResponseIssueCommand cmd) {
        var ri = responseIssueRepository.findById(cmd.getResponseId())
                .orElseThrow(() -> new ResourceNotFoundException("ResponseIssue", cmd.getResponseId()));
        ri.update(cmd.getResponseTitle(), cmd.getResponseDescription());
        if (cmd.getImportant() != null) ri.setImportant(cmd.getImportant());
        return responseIssueRepository.save(ri).getId();
    }
}
