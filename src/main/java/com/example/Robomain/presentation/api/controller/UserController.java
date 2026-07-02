package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.user.command.CreateUserCommand;
import com.example.Robomain.application.user.command.UpdateUserCommand;
import com.example.Robomain.application.user.dto.UserDto;
import com.example.Robomain.application.user.handler.CreateUserCommandHandler;
import com.example.Robomain.application.user.handler.GetUserByIdQueryHandler;
import com.example.Robomain.application.user.handler.ListUsersQueryHandler;
import com.example.Robomain.application.user.handler.UpdateUserCommandHandler;
import com.example.Robomain.application.user.query.GetUserByIdQuery;
import com.example.Robomain.application.user.query.ListUsersQuery;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.USER_PATH)
@RequiredArgsConstructor
public class UserController {

    private final CreateUserCommandHandler createHandler;
    private final UpdateUserCommandHandler updateHandler;
    private final GetUserByIdQueryHandler getByIdHandler;
    private final ListUsersQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> create(@Valid @RequestBody CreateUserCommand command) {
        UUID id = createHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetUserByIdQuery(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> update(
            @PathVariable UUID id,
            @RequestBody UpdateUserCommand command) {
        command.setUserId(id);
        UUID updatedId = updateHandler.handle(command);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetUserByIdQuery(updatedId))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetUserByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<UserDto>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(ApiResponse.success(
                listHandler.handle(new ListUsersQuery(page, size, search))));
    }
}
