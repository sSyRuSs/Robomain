package com.example.Robomain.application.chat.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetConversationByIdQuery {
    private final UUID conversationId;
}
