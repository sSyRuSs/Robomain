package com.example.Robomain.application.chat.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ListMessagesQuery {
    private final UUID conversationId;
    private final int page;
    private final int size;
}
