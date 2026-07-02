package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.response_issue.command.*;
import com.example.Robomain.application.response_issue.dto.ResponseIssueDto;
import com.example.Robomain.application.response_issue.handler.*;
import com.example.Robomain.application.response_issue.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.RESPONSE_ISSUE_PATH)
@RequiredArgsConstructor
public class ResponseIssueController {

    private final CreateResponseIssueCommandHandler createHandler;
    private final UpdateResponseIssueCommandHandler updateHandler;
    private final GetResponseIssueByIdQueryHandler getByIdHandler;
    private final ListResponseIssuesByIssueQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<ResponseIssueDto>> create(
            @Valid @RequestBody CreateResponseIssueCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetResponseIssueByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseIssueDto>> update(
            @PathVariable UUID id, @RequestBody UpdateResponseIssueCommand cmd) {
        cmd.setResponseId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetResponseIssueByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseIssueDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetResponseIssueByIdQuery(id))));
    }

    /** List responses for a task-detail issue. Use parentId=null for top-level responses. */
    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<ResponseIssueDto>>> list(
            @RequestParam UUID issueId,
            @RequestParam(required = false) UUID parentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListResponseIssuesByIssueQuery(issueId, parentId, page, size))));
    }
}
