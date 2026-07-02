package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.task_detail.command.CompleteTaskDetailCommand;
import com.example.Robomain.application.task_detail.command.CreateTaskDetailCommand;
import com.example.Robomain.application.task_detail.command.UpdateTaskDetailCommand;
import com.example.Robomain.application.task_detail.dto.TaskDetailDto;
import com.example.Robomain.application.task_detail.handler.*;
import com.example.Robomain.application.task_detail.query.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.TASK_DETAIL_PATH)
@RequiredArgsConstructor
public class TaskDetailController {

    private final CreateTaskDetailCommandHandler createHandler;
    private final UpdateTaskDetailCommandHandler updateHandler;
    private final CompleteTaskDetailCommandHandler completeHandler;
    private final GetTaskDetailByIdQueryHandler getByIdHandler;
    private final ListTaskDetailsQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskDetailDto>> create(@Valid @RequestBody CreateTaskDetailCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetTaskDetailByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDetailDto>> update(
            @PathVariable UUID id, @RequestBody UpdateTaskDetailCommand cmd) {
        cmd.setTaskDetailId(id);
        UUID updatedId = updateHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetTaskDetailByIdQuery(updatedId))));
    }

    /** Mark a task detail item as completed. */
    @PatchMapping("/{id}/complete")
    public ResponseEntity<ApiResponse<TaskDetailDto>> complete(@PathVariable UUID id) {
        UUID updatedId = completeHandler.handle(new CompleteTaskDetailCommand(id));
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetTaskDetailByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDetailDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetTaskDetailByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<TaskDetailDto>>> listByTask(
            @RequestParam UUID taskId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(
                listHandler.handle(new ListTaskDetailsQuery(taskId, page, size))));
    }
}
