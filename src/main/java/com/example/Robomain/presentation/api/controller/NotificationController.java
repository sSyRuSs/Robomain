package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.notification.command.*;
import com.example.Robomain.application.notification.dto.NotificationDto;
import com.example.Robomain.application.notification.handler.*;
import com.example.Robomain.application.notification.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.NOTIFICATION_PATH)
@RequiredArgsConstructor
public class NotificationController {

    private final CreateNotificationCommandHandler createHandler;
    private final MarkNotificationAsReadCommandHandler markAsReadHandler;
    private final MarkAllNotificationsAsReadCommandHandler markAllAsReadHandler;
    private final GetNotificationByIdQueryHandler getByIdHandler;
    private final ListNotificationsQueryHandler listHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<NotificationDto>> create(
            @Valid @RequestBody CreateNotificationCommand cmd) {
        UUID id = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getByIdHandler.handle(new GetNotificationByIdQuery(id))));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<ApiResponse<NotificationDto>> markAsRead(@PathVariable UUID id) {
        var cmd = new MarkNotificationAsReadCommand();
        cmd.setNotificationId(id);
        UUID updatedId = markAsReadHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetNotificationByIdQuery(updatedId))));
    }

    @PatchMapping("/read-all")
    public ResponseEntity<ApiResponse<Void>> markAllAsRead(@RequestParam UUID userId) {
        var cmd = new MarkAllNotificationsAsReadCommand();
        cmd.setUserId(userId);
        markAllAsReadHandler.handle(cmd);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NotificationDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(
                getByIdHandler.handle(new GetNotificationByIdQuery(id))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<NotificationDto>>> list(
            @RequestParam UUID userId,
            @RequestParam(defaultValue = "false") boolean unreadOnly,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(ApiResponse.success(listHandler.handle(
                new ListNotificationsQuery(userId, unreadOnly, page, size))));
    }
}
