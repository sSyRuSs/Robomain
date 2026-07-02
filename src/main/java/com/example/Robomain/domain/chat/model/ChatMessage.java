package com.example.Robomain.domain.chat.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ChatMessage — a message within a ChatConversation.
 * messageType: TEXT, IMAGE, FILE, SYSTEM
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private UUID id;
    private UUID conversationId;
    private UUID senderId;
    private String content;
    @Builder.Default
    private String messageType = "TEXT";
    private String clientMessageId;  // client-generated idempotency key
    @Builder.Default
    private boolean deleted = false;
    @Builder.Default
    private boolean pinned = false;
    private Date createdAt;
    private Date updatedAt;

    public static ChatMessage send(UUID conversationId, UUID senderId, String content,
                                    String messageType, String clientMessageId) {
        if (conversationId == null) throw new ValidationException("Conversation is required");
        if (senderId == null) throw new ValidationException("Sender is required");
        if (content == null || content.isBlank()) throw new ValidationException("Message content cannot be blank");
        return ChatMessage.builder()
                .id(UUID.randomUUID()).conversationId(conversationId).senderId(senderId)
                .content(content).messageType(messageType != null ? messageType : "TEXT")
                .clientMessageId(clientMessageId).deleted(false).pinned(false)
                .build();
    }

    public void softDelete() { this.deleted = true; }
    public void togglePin()  { this.pinned = !this.pinned; }
}
