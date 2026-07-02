package com.example.Robomain.domain.chat.model;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ChatConversation — a conversation thread (DIRECT between two users, or GROUP/ROOM).
 * Conversations are scoped to an enterprise.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatConversation {

    public static final String TYPE_DIRECT = "DIRECT";
    public static final String TYPE_GROUP  = "GROUP";

    private UUID id;
    @Builder.Default
    private String type = TYPE_DIRECT;
    private String name;
    private UUID departmentId;  // optional: for department-scoped group chats
    private UUID enterpriseId;
    private UUID createdBy;
    @Builder.Default
    private boolean deleted = false;
    @Builder.Default
    private boolean canSendMessages = true;
    private Date createdAt;
    private Date updatedAt;

    public static ChatConversation create(String type, String name, UUID departmentId,
                                           UUID enterpriseId, UUID createdBy) {
        return ChatConversation.builder()
                .id(UUID.randomUUID())
                .type(type != null ? type : TYPE_DIRECT).name(name)
                .departmentId(departmentId).enterpriseId(enterpriseId).createdBy(createdBy)
                .deleted(false).canSendMessages(true)
                .build();
    }

    public void softDelete() { this.deleted = true; this.canSendMessages = false; }
    public void lockMessaging() { this.canSendMessages = false; }
    public void unlockMessaging() { this.canSendMessages = true; }
}
