package com.example.Robomain.presentation.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Robomain.application.chat.command.*;
import com.example.Robomain.application.chat.dto.ChatConversationDto;
import com.example.Robomain.application.chat.dto.ChatMessageDto;
import com.example.Robomain.application.chat.handler.*;
import com.example.Robomain.application.chat.query.*;
import com.example.Robomain.application.shared.Constants;
import com.example.Robomain.application.shared.response.ApiResponse;
import com.example.Robomain.application.shared.response.PaginationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.CHAT_PATH)
@RequiredArgsConstructor
public class ChatController {

    private final CreateConversationCommandHandler createConversationHandler;
    private final GetConversationByIdQueryHandler getConversationHandler;
    private final SendMessageCommandHandler sendMessageHandler;
    private final ListMessagesQueryHandler listMessagesHandler;

    @PostMapping("/conversations")
    public ResponseEntity<ApiResponse<ChatConversationDto>> createConversation(
            @Valid @RequestBody CreateConversationCommand cmd) {
        UUID id = createConversationHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(getConversationHandler.handle(new GetConversationByIdQuery(id))));
    }

    @GetMapping("/conversations/{id}")
    public ResponseEntity<ApiResponse<ChatConversationDto>> getConversation(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(
                getConversationHandler.handle(new GetConversationByIdQuery(id))));
    }

    @PostMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<ApiResponse<UUID>> sendMessage(
            @PathVariable UUID conversationId, @Valid @RequestBody SendMessageCommand cmd) {
        cmd.setConversationId(conversationId);
        UUID id = sendMessageHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(id));
    }

    @GetMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<ApiResponse<PaginationResponse<ChatMessageDto>>> listMessages(
            @PathVariable UUID conversationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(ApiResponse.success(listMessagesHandler.handle(
                new ListMessagesQuery(conversationId, page, size))));
    }
}
