package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.task.command.CreateTaskCommand;
import com.example.Robomain.application.task.command.UpdateTaskCommand;
import com.example.Robomain.application.task.dto.TaskDto;
import com.example.Robomain.application.task.handler.*;
import com.example.Robomain.application.task.query.*;
import com.example.Robomain.domain.shared.enums.EnumStatus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.TASK_PATH)
@RequiredArgsConstructor
public class TaskController {

    private final CreateTaskCommandHandler createHandler;
    private final UpdateTaskCommandHandler updateHandler;
    private final GetTaskByIdQueryHandler getByIdHandler;
    private final ListTasksQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskDto>> create(@Valid @RequestBody CreateTaskCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetTaskByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> update(
            @PathVariable UUID id, @RequestBody UpdateTaskCommand cmd) {
        cmd.setTaskId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetTaskByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetTaskByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<TaskDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) EnumStatus status,
            @RequestParam(required = false) UUID workOrderId,
            @RequestParam(required = false) UUID userId) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListTasksQuery(page, size, search, status, workOrderId, userId))));
    }
}
